#!/usr/bin/env python3

import argparse
import csv
import html
import hashlib
import json
import math
import os
import random
import re
import shutil
import subprocess
import sys
from collections import Counter
from pathlib import Path
from typing import Dict, Iterable, List, Optional


# ============================================================
# Configuration
# ============================================================

DEFAULT_LANGUAGES = ["Python", "Java", "C++", "C#"]
DEFAULT_HUMAN_PER_PROBLEM = 3
DEFAULT_SEED = 4050

# External-process timeouts for AI-provider CLIs. Metric analysis runs
# in-process through Lizard and therefore has no 5-second per-file timeout.
AI_GENERATION_TIMEOUT_SECONDS = int(os.environ.get("THESIS_AI_TIMEOUT", "300"))
MODEL_PROBE_TIMEOUT_SECONDS = int(os.environ.get("THESIS_MODEL_PROBE_TIMEOUT", "90"))

LANGUAGE_EXTENSIONS = {
    "Python": ".py",
    "Java": ".java",
    "C++": ".cpp",
    "C": ".c",
    "C#": ".cs",
    "JavaScript": ".js",
    "TypeScript": ".ts",
    "Go": ".go",
    "Rust": ".rs",
    "Ruby": ".rb",
    "PHP": ".php",
}

WORK_DIR = Path("thesis_work")
MANIFEST_DIR = WORK_DIR / "manifests"
SAMPLE_DIR = WORK_DIR / "samples"
PROMPT_DIR = WORK_DIR / "prompts"
CLAUDE_DIR = WORK_DIR / "claude_batches"
RESULT_DIR = WORK_DIR / "results"


# ============================================================
# General helpers
# ============================================================

def ensure_dirs():
    for p in [MANIFEST_DIR, SAMPLE_DIR, PROMPT_DIR, CLAUDE_DIR, RESULT_DIR]:
        p.mkdir(parents=True, exist_ok=True)


def safe_name(value: str) -> str:
    value = re.sub(r"[^A-Za-z0-9._+-]+", "_", str(value)).strip("_")
    return value or "unknown"


def normalise_language(value: str) -> str:
    aliases = {
        "python": "Python",
        "python3": "Python",
        "java": "Java",
        "c++": "C++",
        "cpp": "C++",
        "c": "C",
        "c#": "C#",
        "csharp": "C#",
        "javascript": "JavaScript",
        "js": "JavaScript",
        "typescript": "TypeScript",
        "ts": "TypeScript",
        "go": "Go",
        "golang": "Go",
        "rust": "Rust",
        "ruby": "Ruby",
        "php": "PHP",
    }
    key = value.strip().lower()
    return aliases.get(key, value.strip())


def extension_for(language: str) -> str:
    language = normalise_language(language)
    if language not in LANGUAGE_EXTENSIONS:
        raise ValueError(f"No configured extension for language: {language}")
    return LANGUAGE_EXTENSIONS[language]


def strip_html(raw: str) -> str:
    raw = re.sub(r"(?is)<script.*?>.*?</script>", " ", raw)
    raw = re.sub(r"(?is)<style.*?>.*?</style>", " ", raw)
    raw = re.sub(r"(?s)<[^>]+>", " ", raw)
    raw = html.unescape(raw)
    raw = re.sub(r"\s+", " ", raw).strip()
    return raw


def read_jsonl(path: Path) -> List[dict]:
    rows = []
    with path.open("r", encoding="utf-8") as f:
        for line_no, line in enumerate(f, start=1):
            line = line.strip()
            if not line:
                continue
            try:
                rows.append(json.loads(line))
            except json.JSONDecodeError as exc:
                raise ValueError(f"Invalid JSONL at {path}:{line_no}: {exc}") from exc
    return rows


def write_jsonl(path: Path, rows: Iterable[dict]):
    path.parent.mkdir(parents=True, exist_ok=True)
    with path.open("w", encoding="utf-8") as f:
        for row in rows:
            f.write(json.dumps(row, ensure_ascii=False) + "\n")


def clean_code(text: str) -> str:
    text = text.strip()
    match = re.search(r"```(?:[A-Za-z0-9_+#.-]+)?\s*(.*?)```", text, re.S)
    if match:
        return match.group(1).strip()
    return text


def run_process(
    command: List[str],
    stdin_text: Optional[str] = None,
    timeout: Optional[int] = None,
) -> subprocess.CompletedProcess:
    """Run a child process and optionally enforce a hard timeout."""
    return subprocess.run(
        command,
        input=stdin_text,
        capture_output=True,
        text=True,
        encoding="utf-8",
        errors="replace",
        timeout=timeout,
    )


# ============================================================
# Unified problem format
# ============================================================

"""
Every prepared problem is stored as one JSONL row:

{
  "dataset": "CodeNet",
  "problem_id": "p00001",
  "language": "Python",
  "problem": "...",
  "human_files": ["path1.py", "path2.py", ...],
  "human_samples": [{"file": "path1.py", "status": "Accepted"}],
  "ai_samples": [{"file": "ai.py", "provider": "ChatGPT", "model": "..."}],
  "test_command": null,
  "metadata": {...}
}

MultiPL-E / CodeContests can be normalised into the same structure.
"""



# ============================================================
# Paired-code dataset helpers
# ============================================================

def load_records_auto(path: Path) -> List[dict]:
    """Load records from JSON array, JSONL/NDJSON, CSV, or a directory of them."""
    path = path.resolve()
    if path.is_dir():
        rows = []
        for child in sorted(path.rglob('*')):
            if child.is_file() and child.suffix.lower() in {'.json', '.jsonl', '.ndjson', '.csv'}:
                try:
                    rows.extend(load_records_auto(child))
                except Exception as exc:
                    print(f"WARN skipping {child}: {exc}")
        return rows

    suffix = path.suffix.lower()
    if suffix == '.csv':
        with path.open('r', encoding='utf-8-sig', errors='ignore', newline='') as f:
            return list(csv.DictReader(f))

    text = path.read_text(encoding='utf-8-sig', errors='ignore').strip()
    if not text:
        return []

    # Some datasets use a .jsonl extension for a single JSON array.
    try:
        obj = json.loads(text)
        if isinstance(obj, list):
            return [r for r in obj if isinstance(r, dict)]
        if isinstance(obj, dict):
            for key in ('data', 'records', 'samples', 'items'):
                if isinstance(obj.get(key), list):
                    return [r for r in obj[key] if isinstance(r, dict)]
            return [obj]
    except json.JSONDecodeError:
        pass

    rows = []
    for line_no, line in enumerate(text.splitlines(), start=1):
        line = line.strip()
        if not line:
            continue
        try:
            obj = json.loads(line)
        except json.JSONDecodeError as exc:
            raise ValueError(f"Could not parse {path} at line {line_no}: {exc}") from exc
        if isinstance(obj, dict):
            rows.append(obj)
    return rows


def first_value(record: dict, *names, default=''):
    lower = {str(k).lower(): v for k, v in record.items()}
    for name in names:
        value = lower.get(name.lower())
        if value not in (None, ''):
            return value
    return default


def write_extracted_code(base: Path, dataset: str, problem_id: str, language: str, category: str, name: str, code: str) -> Path:
    folder = base / safe_name(dataset) / safe_name(problem_id) / safe_name(language) / category
    folder.mkdir(parents=True, exist_ok=True)
    path = folder / f"{safe_name(name)}{extension_for(language)}"
    path.write_text(str(code).strip(), encoding='utf-8')
    return path.resolve()


def prepare_back3474(input_path: Path, output: Path, extract_dir: Path, languages: List[str]):
    """Convert the Back3474 paired dataset into the thesis manifest format."""
    allowed = {normalise_language(x) for x in languages}
    records = load_records_auto(input_path)
    grouped = {}

    for rec in records:
        language = normalise_language(str(rec.get('language', '')))
        if language not in allowed:
            continue
        problem_no = str(rec.get('problem_number', rec.get('id', 'unknown')))
        key = (problem_no, language)
        row = grouped.setdefault(key, {
            'dataset': 'Back3474',
            'problem_id': f"problem_{problem_no}",
            'language': language,
            'problem': rec.get('description', ''),
            'human_files': [],
            'human_samples': [],
            'ai_samples': [],
            'test_command': None,
            'metadata': {
                'difficulty': rec.get('difficulty', ''),
                'source_repository': 'Back3474/AI-Human-Generated-Program-Code-Dataset',
            },
        })

        human_code = rec.get('human_generated_code', '')
        if human_code:
            human_path = write_extracted_code(
                extract_dir, 'Back3474', row['problem_id'], language, 'human',
                'human_reference', human_code
            )
            if str(human_path) not in row['human_files']:
                row['human_files'].append(str(human_path))
                row['human_samples'].append({
                    'file': str(human_path),
                    'status': 'dataset_provided',
                    'difficulty': rec.get('difficulty', ''),
                })

        ai_code = rec.get('ai_generated_code', '')
        if ai_code:
            generator = str(rec.get('generator_name', 'unknown_ai'))
            rec_id = str(rec.get('id', len(row['ai_samples']) + 1))
            ai_path = write_extracted_code(
                extract_dir, 'Back3474', row['problem_id'], language, 'ai',
                f"{generator}_{rec_id}", ai_code
            )
            row['ai_samples'].append({
                'file': str(ai_path),
                'provider': generator,
                'model': generator,
                'generator_type': rec.get('generator_type', ''),
                'prompt_type': 'dataset_original',
                'prompt': rec.get('prompt', ''),
                'scenario': 'generation_from_problem',
                'status': 'dataset_provided_not_revalidated',
                'difficulty': rec.get('difficulty', ''),
            })

    rows = list(grouped.values())
    write_jsonl(output, rows)
    print(f"Prepared {len(rows)} Back3474 problem/language rows from {len(records)} records -> {output}")


def normalise_sample_entry(entry, default_status='dataset_provided') -> dict:
    if isinstance(entry, str):
        return {'file': entry, 'status': default_status}
    if isinstance(entry, dict):
        return entry.copy()
    return {}

def stage_existing_row(experiment: int, row: dict, include_human: bool = True, include_ai: bool = True):
    """Copy already-existing human/AI samples from a manifest into an experiment folder."""
    if include_human:
        entries = row.get('human_samples') or row.get('human_files', [])
        seen = set()
        run_no = 0
        for raw in entries:
            entry = normalise_sample_entry(raw, 'dataset_provided')
            src_text = entry.get('file', '')
            if not src_text or src_text in seen:
                continue
            seen.add(src_text)
            src = Path(src_text)
            if not src.exists():
                continue
            run_no += 1
            dst = sample_file(experiment, row, 'Human', 'human', 'original', run_no)
            if not dst.exists():
                shutil.copy2(src, dst)
            meta = {
                'experiment': experiment,
                'dataset': row['dataset'],
                'problem_id': row['problem_id'],
                'language': row['language'],
                'source': 'Human',
                'provider': '',
                'model': '',
                'model_group': 'human',
                'prompt_type': 'original',
                'run': run_no,
                'correctness': entry.get('status', 'dataset_provided'),
                'difficulty': entry.get('difficulty', row.get('metadata', {}).get('difficulty', '')),
                'scenario': entry.get('scenario', ''),
                'original_file': str(src),
            }
            metadata_file_for(dst).write_text(json.dumps(meta, indent=2, ensure_ascii=False), encoding='utf-8')

    if include_ai:
        for run_no, raw in enumerate(row.get('ai_samples', []), start=1):
            entry = normalise_sample_entry(raw, 'dataset_provided_not_revalidated')
            src = Path(entry.get('file', ''))
            if not src.exists():
                continue
            provider = str(entry.get('provider') or entry.get('model') or 'dataset_ai')
            model = str(entry.get('model') or provider)
            prompt_type = str(entry.get('prompt_type') or 'dataset_original')
            dst = sample_file(experiment, row, provider, model, prompt_type, run_no)
            if not dst.exists():
                shutil.copy2(src, dst)
            meta = {
                'experiment': experiment,
                'dataset': row['dataset'],
                'problem_id': row['problem_id'],
                'language': row['language'],
                'source': 'AI',
                'provider': provider,
                'model': model,
                'model_group': model,
                'prompt_type': prompt_type,
                'run': run_no,
                'correctness': entry.get('status', 'dataset_provided_not_revalidated'),
                'difficulty': entry.get('difficulty', row.get('metadata', {}).get('difficulty', '')),
                'scenario': entry.get('scenario', ''),
                'generator_type': entry.get('generator_type', ''),
                'original_file': str(src),
            }
            metadata_file_for(dst).write_text(json.dumps(meta, indent=2, ensure_ascii=False), encoding='utf-8')

def stage_existing_manifest(manifest: Path, experiment: int, languages: List[str], datasets: Optional[List[str]] = None):
    allowed_languages = {normalise_language(x) for x in languages} if languages else None
    allowed_datasets = {x.lower() for x in datasets} if datasets else None
    count = 0
    for row in read_jsonl(manifest):
        if allowed_languages and normalise_language(row['language']) not in allowed_languages:
            continue
        if allowed_datasets and row['dataset'].lower() not in allowed_datasets:
            continue
        stage_existing_row(experiment, row, True, True)
        count += 1
    print(f"Staged existing samples for {count} problem/language rows into Experiment {experiment}.")

def inspect_manifest(manifest: Path):
    rows = read_jsonl(manifest)
    counts = {}
    for row in rows:
        key = (row.get('dataset', ''), row.get('language', ''))
        human_n = len(row.get('human_samples') or row.get('human_files', []))
        ai_n = len(row.get('ai_samples', []))
        current = counts.setdefault(key, {'rows': 0, 'human': 0, 'ai': 0})
        current['rows'] += 1
        current['human'] += human_n
        current['ai'] += ai_n
    print('Dataset / Language summary')
    for (dataset, language), values in sorted(counts.items()):
        print(f"{dataset:18} {language:12} rows={values['rows']:5} human={values['human']:6} ai={values['ai']:6}")


# ============================================================
# Human-vs-AI Code Quality dataset
# ============================================================

HVAI_AUTHOR_FIELDS = {
    "Human": ("human_code", "human-code", "human"),
    "ChatGPT": ("chatgpt_code", "chatgpt-code", "chatgpt"),
    "DeepSeek-Coder": ("dsc_code", "deepseek_code", "deepseek-coder-code", "deepseek"),
    "Qwen-Coder": ("qwen_code", "qwen-coder-code", "qwen"),
}

def _field_ci(record: dict, aliases, default=""):
    """Case-insensitive lookup using several possible field names."""
    lowered = {str(k).strip().lower(): v for k, v in record.items()}
    for alias in aliases:
        key = alias.strip().lower()
        if key in lowered and lowered[key] not in (None, ""):
            return lowered[key]
    return default

def _metric_value(metrics: dict, key: str, digits: int = 2):
    value = metrics.get(key)
    if value is None:
        return ""
    try:
        return round(float(value), digits)
    except (TypeError, ValueError):
        return value


def analyse_hvai_quality(
    python_jsonl: Optional[Path],
    java_jsonl: Optional[Path],
    output_csv: Path,
    max_records: int,
    batch_size: int,
    authors: List[str],
):
    """
    Stream the public Human-vs-AI Code Quality dataset and analyse every
    selected sample with the same Lizard-based metric pipeline used by
    Experiments 1-4.

    Lizard supplies NLOC/CCN and token classification for Halstead metrics.
    Maintainability Index is then calculated from those measurements.
    """
    if not lizard_available():
        raise RuntimeError(
            "Lizard with the Halstead extension is not installed.\n"
            "Install/update it with: python -m pip install --upgrade lizard"
        )

    sources = []
    if python_jsonl:
        sources.append(("Python", python_jsonl))
    if java_jsonl:
        sources.append(("Java", java_jsonl))
    if not sources:
        raise ValueError("Provide --python and/or --java.")

    wanted = []
    author_lookup = {k.lower(): k for k in HVAI_AUTHOR_FIELDS}
    for author in authors:
        canonical = author_lookup.get(author.lower())
        if not canonical:
            raise ValueError(
                f"Unknown author '{author}'. Choose from: "
                + ", ".join(HVAI_AUTHOR_FIELDS)
            )
        wanted.append(canonical)

    fieldnames = [
        "experiment",
        "dataset",
        "record_index",
        "problem_id",
        "language",
        "source",
        "provider",
        "model",
        "prompt_type",
        "correctness",
        "metric_language",
        "mi",
        "cc",
        "loc",
        "halstead_volume",
        "halstead_difficulty",
        "halstead_effort",
        "comment_ratio",
        "operators_total",
        "operators_unique",
        "operands_total",
        "operands_unique",
        "metric_error",
    ]

    output_csv.parent.mkdir(parents=True, exist_ok=True)
    if output_csv.exists():
        raise FileExistsError(
            f"Output already exists: {output_csv}. Delete it or choose another --output path."
        )

    total_records = 0
    total_samples = 0

    import tempfile

    with output_csv.open("w", newline="", encoding="utf-8") as out_f:
        writer = csv.DictWriter(out_f, fieldnames=fieldnames)
        writer.writeheader()

        for language, dataset_file in sources:
            language_records = 0
            dataset_file = dataset_file.resolve()
            if not dataset_file.exists():
                raise FileNotFoundError(dataset_file)

            extension = extension_for(language)
            batch = []

            def flush_batch():
                nonlocal total_samples
                if not batch:
                    return

                with tempfile.TemporaryDirectory(prefix="thesis_hvai_") as temp_name:
                    temp_dir = Path(temp_name)

                    for batch_pos, item in enumerate(batch, start=1):
                        code_file = temp_dir / (
                            f"r{item['record_index']:08d}_"
                            f"{safe_name(item['source'])}_{batch_pos}{extension}"
                        )
                        code_file.write_text(item["code"], encoding="utf-8")

                        try:
                            metrics = analyse_with_lizard(code_file)
                            metric_error = ""
                        except Exception as exc:
                            metrics = _empty_metric_result()
                            metric_error = str(exc)

                        writer.writerow({
                            "experiment": 1,
                            "dataset": "Human_vs_AI_Code_Quality",
                            "record_index": item["record_index"],
                            "problem_id": item["problem_id"],
                            "language": language,
                            "source": item["source"],
                            "provider": item["provider"],
                            "model": item["model"],
                            "prompt_type": "dataset_original",
                            "correctness": "dataset_provided_not_revalidated",
                            **metrics,
                            "metric_error": metric_error,
                        })
                        total_samples += 1

                    out_f.flush()
                batch.clear()

            with dataset_file.open("r", encoding="utf-8-sig", errors="ignore") as f:
                for line_number, line in enumerate(f, start=1):
                    line = line.strip()
                    if not line:
                        continue
                    try:
                        record = json.loads(line)
                    except json.JSONDecodeError as exc:
                        print(f"WARN {dataset_file}:{line_number}: {exc}")
                        continue

                    total_records += 1
                    language_records += 1
                    if max_records and language_records > max_records:
                        break

                    record_index = _field_ci(record, ("index", "id"), line_number)
                    problem_id = _field_ci(
                        record,
                        ("problem_id", "task_id", "index", "id"),
                        record_index,
                    )

                    for author in wanted:
                        code = _field_ci(record, HVAI_AUTHOR_FIELDS[author], "")
                        if not isinstance(code, str) or not code.strip():
                            continue

                        provider = "" if author == "Human" else author
                        batch.append({
                            "record_index": record_index,
                            "problem_id": problem_id,
                            "source": "Human" if author == "Human" else "AI",
                            "provider": provider,
                            "model": provider,
                            "code": clean_code(code),
                        })

                        if len(batch) >= batch_size:
                            flush_batch()

                flush_batch()

    print(
        f"Human-vs-AI Code Quality analysis complete: "
        f"{total_records} records inspected, {total_samples} code samples measured -> {output_csv}"
    )

# ============================================================
# Results summarisation
# ============================================================

def summarise_results(input_csv: Path, output_csv: Path, groups: List[str]):
    """Create mean/median/stdev summaries for MI, CC, LOC and Halstead Volume."""
    import statistics

    metrics = ["mi", "cc", "loc", "halstead_volume"]
    buckets = {}

    with input_csv.open("r", encoding="utf-8-sig", newline="") as f:
        reader = csv.DictReader(f)
        missing = [g for g in groups if g not in (reader.fieldnames or [])]
        if missing:
            raise ValueError(f"Grouping columns not found in CSV: {missing}")

        for row in reader:
            key = tuple(row.get(g, "") for g in groups)
            bucket = buckets.setdefault(key, {m: [] for m in metrics})
            for metric in metrics:
                raw = row.get(metric, "")
                try:
                    value = float(raw)
                except (TypeError, ValueError):
                    continue
                bucket[metric].append(value)

    fieldnames = list(groups) + ["n"]
    for metric in metrics:
        fieldnames += [
            f"{metric}_mean",
            f"{metric}_median",
            f"{metric}_stdev",
            f"{metric}_min",
            f"{metric}_max",
        ]

    output_csv.parent.mkdir(parents=True, exist_ok=True)
    with output_csv.open("w", encoding="utf-8", newline="") as f:
        writer = csv.DictWriter(f, fieldnames=fieldnames)
        writer.writeheader()

        for key, bucket in sorted(buckets.items()):
            row = {g: value for g, value in zip(groups, key)}
            all_lengths = [len(bucket[m]) for m in metrics]
            row["n"] = max(all_lengths) if all_lengths else 0

            for metric in metrics:
                values = bucket[metric]
                if values:
                    row[f"{metric}_mean"] = round(statistics.fmean(values), 3)
                    row[f"{metric}_median"] = round(statistics.median(values), 3)
                    row[f"{metric}_stdev"] = (
                        round(statistics.stdev(values), 3) if len(values) > 1 else 0.0
                    )
                    row[f"{metric}_min"] = round(min(values), 3)
                    row[f"{metric}_max"] = round(max(values), 3)
                else:
                    for suffix in ("mean", "median", "stdev", "min", "max"):
                        row[f"{metric}_{suffix}"] = ""

            writer.writerow(row)

    print(f"Summary written -> {output_csv}")


# ============================================================
# Project CodeNet preparation
# ============================================================

def load_codenet_problem_names(root: Path) -> Dict[str, dict]:
    problem_list = root / "metadata" / "problem_list.csv"
    if not problem_list.exists():
        raise FileNotFoundError(
            f"Could not find {problem_list}. Point --root at the extracted Project_CodeNet folder."
        )

    out = {}
    with problem_list.open("r", encoding="utf-8", errors="ignore", newline="") as f:
        reader = csv.DictReader(f)
        for row in reader:
            pid = row.get("id") or row.get("problem_id")
            if pid:
                out[pid] = row
    return out


def read_codenet_description(root: Path, problem_id: str, fallback_name: str = "") -> str:
    candidates = [
        root / "problem_descriptions" / f"{problem_id}.html",
        root / "doc" / "problem_descriptions" / f"{problem_id}.html",
    ]

    for path in candidates:
        if path.exists():
            text = strip_html(path.read_text(encoding="utf-8", errors="ignore"))
            if text:
                return text

    return fallback_name or problem_id


def prepare_codenet(
    root: Path,
    output: Path,
    languages: List[str],
    human_per_problem: int,
    max_problems: int,
    seed: int,
):
    """
    Select accepted CodeNet submissions automatically.

    max_problems = 0 means no explicit limit.
    """
    root = root.resolve()
    languages = [normalise_language(x) for x in languages]
    rng = random.Random(seed)

    problem_info = load_codenet_problem_names(root)
    metadata_dir = root / "metadata"

    rows = []
    problem_counter = 0

    for metadata_file in sorted(metadata_dir.glob("p*.csv")):
        problem_id = metadata_file.stem
        by_language: Dict[str, List[Path]] = {lang: [] for lang in languages}

        with metadata_file.open("r", encoding="utf-8", errors="ignore", newline="") as f:
            reader = csv.DictReader(f)
            for row in reader:
                if row.get("status") != "Accepted":
                    continue

                language = normalise_language(row.get("language", ""))
                if language not in by_language:
                    continue

                submission_id = row.get("submission_id", "")
                ext = row.get("filename_ext", "").lstrip(".")
                if not submission_id or not ext:
                    continue

                source = root / "data" / problem_id / language / f"{submission_id}.{ext}"
                if source.exists():
                    by_language[language].append(source)

        eligible_languages = [
            lang for lang, paths in by_language.items()
            if len(paths) >= human_per_problem
        ]

        if not eligible_languages:
            continue

        info = problem_info.get(problem_id, {})
        problem_text = read_codenet_description(
            root,
            problem_id,
            fallback_name=info.get("name", problem_id),
        )

        for language in eligible_languages:
            paths = by_language[language][:]
            rng.shuffle(paths)
            selected = paths[:human_per_problem]

            rows.append({
                "dataset": "CodeNet",
                "problem_id": problem_id,
                "language": language,
                "problem": problem_text,
                "human_files": [str(p) for p in selected],
                "test_command": None,
                "metadata": {
                    "name": info.get("name", ""),
                    "original_dataset": info.get("dataset", ""),
                    "rating": info.get("rating", ""),
                    "selection_seed": seed,
                    "human_per_problem": human_per_problem,
                },
            })

        problem_counter += 1
        if max_problems and problem_counter >= max_problems:
            break

    write_jsonl(output, rows)
    print(f"Prepared {len(rows)} CodeNet problem/language rows -> {output}")


# ============================================================
# Generic dataset normalisation
# ============================================================

def normalise_external_jsonl(
    input_path: Path,
    output_path: Path,
    dataset_name: str,
):
    """
    Normalises a simple JSONL export from MultiPL-E or CodeContests.

    Accepted input keys (aliases supported):
      problem_id / id / name
      language / lang
      problem / prompt / description
      human_files (optional)
      test_command (optional)

    This keeps dataset-specific extraction separate from the experiment engine.
    """
    source_rows = read_jsonl(input_path)
    out = []

    for i, row in enumerate(source_rows, start=1):
        problem_id = (
            row.get("problem_id")
            or row.get("id")
            or row.get("name")
            or f"{dataset_name}_{i:05d}"
        )

        language = normalise_language(
            row.get("language")
            or row.get("lang")
            or ""
        )

        problem = (
            row.get("problem")
            or row.get("prompt")
            or row.get("description")
            or ""
        )

        if not language or not problem:
            continue

        human_files = row.get("human_files", [])
        if isinstance(human_files, str):
            human_files = [human_files]

        out.append({
            "dataset": dataset_name,
            "problem_id": str(problem_id),
            "language": language,
            "problem": problem,
            "human_files": human_files,
            "test_command": row.get("test_command"),
            "metadata": row.get("metadata", {}),
        })

    write_jsonl(output_path, out)
    print(f"Normalised {len(out)} rows -> {output_path}")


def merge_manifests(inputs: List[Path], output: Path):
    rows = []
    seen = set()

    for path in inputs:
        for row in read_jsonl(path):
            key = (
                row.get("dataset"),
                row.get("problem_id"),
                row.get("language"),
            )
            if key in seen:
                continue
            seen.add(key)
            rows.append(row)

    write_jsonl(output, rows)
    print(f"Merged {len(rows)} unique problem/language rows -> {output}")


# ============================================================
# Reproducible study sampling
# ============================================================

def make_study_manifest(
    input_manifest: Path,
    output_manifest: Path,
    problems_per_dataset: int,
    seed: int,
    languages: List[str],
):
    """
    Create a fixed stratified sample for fresh AI generation.

    Sampling is performed by unique problem ID within each dataset. A problem is
    eligible only when every requested language is present. Once a problem is
    selected, all requested language rows for that problem are retained. This
    keeps Experiment 2 paired across languages while ensuring every dataset is
    represented. If a dataset has fewer eligible problems than requested, all
    eligible problems are retained.
    """
    if problems_per_dataset <= 0:
        raise ValueError("--problems-per-dataset must be greater than zero")

    rows = read_jsonl(input_manifest)
    wanted_languages = [normalise_language(x) for x in languages]
    wanted_set = set(wanted_languages)

    by_dataset: Dict[str, Dict[str, List[dict]]] = {}
    for row in rows:
        dataset = str(row.get("dataset", "")).strip()
        problem_id = str(row.get("problem_id", "")).strip()
        language = normalise_language(str(row.get("language", "")))
        if not dataset or not problem_id or language not in wanted_set:
            continue
        by_dataset.setdefault(dataset, {}).setdefault(problem_id, []).append(row)

    selected_rows: List[dict] = []
    selection = {
        "source_manifest": str(input_manifest),
        "output_manifest": str(output_manifest),
        "seed": seed,
        "problems_per_dataset": problems_per_dataset,
        "required_languages": wanted_languages,
        "datasets": {},
    }

    for dataset in sorted(by_dataset):
        problems = by_dataset[dataset]
        eligible = []
        for problem_id, problem_rows in problems.items():
            present = {normalise_language(str(r.get("language", ""))) for r in problem_rows}
            if wanted_set.issubset(present):
                eligible.append(problem_id)

        eligible.sort()
        # Dataset-specific deterministic seed prevents one dataset's size/order
        # from changing the sample selected for another dataset.
        digest = hashlib.sha256(f"{seed}:{dataset}".encode("utf-8")).digest()
        rng = random.Random(int.from_bytes(digest[:8], "big"))
        rng.shuffle(eligible)
        chosen = sorted(eligible[:problems_per_dataset])
        chosen_set = set(chosen)

        for problem_id in chosen:
            problem_rows = problems[problem_id]
            # Keep one row per requested language, in a fixed language order.
            by_lang = {}
            for row in problem_rows:
                lang = normalise_language(str(row.get("language", "")))
                by_lang.setdefault(lang, row)
            for language in wanted_languages:
                if language in by_lang:
                    selected_rows.append(by_lang[language])

        selection["datasets"][dataset] = {
            "eligible_problem_count": len(eligible),
            "selected_problem_count": len(chosen),
            "selected_problem_ids": chosen,
            "selected_row_count": len(chosen) * len(wanted_languages),
        }

    if not selected_rows:
        raise RuntimeError(
            "No study rows were selected. Check that the input manifest contains "
            "the requested languages for the same problem IDs."
        )

    write_jsonl(output_manifest, selected_rows)
    selection_path = output_manifest.with_suffix(".selection.json")
    selection_path.write_text(
        json.dumps(selection, indent=2, ensure_ascii=False),
        encoding="utf-8",
    )

    print(f"Study manifest written -> {output_manifest}")
    print(f"Selection record       -> {selection_path}")
    print(f"Selected rows          -> {len(selected_rows)}")
    for dataset, info in selection["datasets"].items():
        print(
            f"  {dataset}: {info['selected_problem_count']} problems, "
            f"{info['selected_row_count']} rows"
        )


# ============================================================
# Prompt construction
# ============================================================

def standard_prompt(problem: dict) -> str:
    return (
        f"Solve the following programming problem in {problem['language']}.\n\n"
        f"{problem['problem']}\n\n"
        "Return only the complete source code. "
        "Do not include explanations or Markdown code fences."
    )


def maintainability_prompt(problem: dict) -> str:
    return (
        f"Solve the following programming problem in {problem['language']}, "
        "but now prioritise software maintainability, readability, and a clear structure.\n\n"
        f"{problem['problem']}\n\n"
        "Return only the complete source code. "
        "Do not include explanations or Markdown code fences."
    )


# ============================================================
# No-API-key providers
# ============================================================
# ============================================================
# CLI discovery
# ============================================================

def resolve_cli(name: str) -> Optional[str]:
    """
    Resolve an installed CLI robustly on Windows/Git Bash.

    npm-installed tools often appear as .cmd files, while native
    installations may use .exe. A plain shutil.which(name) check can
    therefore miss a CLI that works perfectly from Git Bash.
    """
    candidates = []

    if os.name == "nt":
        candidates.extend([
            f"{name}.cmd",
            f"{name}.exe",
            f"{name}.bat",
            name,
        ])
    else:
        candidates.append(name)

    for candidate in candidates:
        found = shutil.which(candidate)
        if found:
            return found

    # Extra Windows fallback using where.exe.
    if os.name == "nt":
        try:
            result = subprocess.run(
                ["where.exe", name],
                capture_output=True,
                text=True,
                encoding="utf-8",
                errors="replace",
            )
            if result.returncode == 0:
                for line in result.stdout.splitlines():
                    line = line.strip()
                    if line and Path(line).exists():
                        return line
        except OSError:
            pass

    return None


def cli_prefix(name: str) -> List[str]:
    """
    Return the command prefix needed to execute a resolved CLI.

    Windows .cmd/.bat shims are launched through cmd.exe.
    """
    resolved = resolve_cli(name)
    if not resolved:
        raise FileNotFoundError(
            f"{name} CLI was not found. "
            f"Try running '{name} --version' in the same terminal."
        )

    lower = resolved.lower()
    if os.name == "nt" and lower.endswith((".cmd", ".bat")):
        return ["cmd.exe", "/d", "/s", "/c", resolved]

    return [resolved]


def check_ai_tools():
    """Print the AI interfaces used by the revised empirical framework."""
    print("\n=== AI TOOL CHECK ===")

    codex = resolve_cli("codex")
    claude = resolve_cli("claude")

    print(f"ChatGPT / Codex CLI : {'FOUND - ' + codex if codex else 'NOT FOUND'}")
    print(
        f"Claude Code CLI     : "
        f"{'FOUND - ' + claude if claude else 'NOT FOUND'} "
        "(not used for generation)"
    )

    print("\nExperiment support:")
    print("  ChatGPT/Codex  -> automatic through the authenticated Codex CLI")
    print("  Claude         -> MANUAL website batch export/import only")
    print("  GitHub Copilot -> excluded from the revised empirical experiments")

    return {"codex": codex, "claude": claude}



def generate_codex(prompt: str, model: Optional[str] = None) -> str:
    """
    Uses saved Codex CLI authentication.
    Sign in beforehand with the Codex CLI / ChatGPT account.
    """
    cmd = cli_prefix("codex") + [
        "exec",
        "--ephemeral",
        "--skip-git-repo-check",
    ]

    if model:
        cmd += ["--model", model]

    cmd += [prompt]

    result = run_process(cmd, timeout=AI_GENERATION_TIMEOUT_SECONDS)
    if result.returncode != 0:
        raise RuntimeError(f"Codex failed:\n{result.stderr}")

    return clean_code(result.stdout)


def provider_generate(provider: str, prompt: str, model: Optional[str]) -> str:
    provider = provider.lower()

    if provider == "codex":
        return generate_codex(prompt, model)

    raise ValueError(
        f"Provider '{provider}' cannot be run directly. "
        "Use claude-export / claude-import for Claude free/manual batches."
    )


# ============================================================
# Sample naming and metadata
# ============================================================

def sample_folder(experiment: int, row: dict, source: str, model_group: str, prompt_type: str) -> Path:
    return (
        SAMPLE_DIR
        / f"experiment_{experiment}"
        / safe_name(row["dataset"])
        / safe_name(row["problem_id"])
        / safe_name(row["language"])
        / safe_name(source)
        / safe_name(model_group)
        / safe_name(prompt_type)
    )


def sample_file(
    experiment: int,
    row: dict,
    source: str,
    model_group: str,
    prompt_type: str,
    run_number: int,
) -> Path:
    folder = sample_folder(experiment, row, source, model_group, prompt_type)
    folder.mkdir(parents=True, exist_ok=True)
    return folder / f"run_{run_number:03d}{extension_for(row['language'])}"


def metadata_file_for(code_file: Path) -> Path:
    return code_file.with_suffix(code_file.suffix + ".meta.json")


def save_sample(code_file: Path, code: str, meta: dict):
    code_file.parent.mkdir(parents=True, exist_ok=True)
    code_file.write_text(code, encoding="utf-8")
    metadata_file_for(code_file).write_text(
        json.dumps(meta, indent=2, ensure_ascii=False),
        encoding="utf-8",
    )


# ============================================================
# Human sample import
# ============================================================

def copy_human_samples(experiment: int, row: dict):
    for i, src_text in enumerate(row.get("human_files", []), start=1):
        src = Path(src_text)
        if not src.exists():
            continue

        dst = sample_file(
            experiment,
            row,
            source="Human",
            model_group="human",
            prompt_type="original",
            run_number=i,
        )

        if not dst.exists():
            shutil.copy2(src, dst)

        meta = {
            "experiment": experiment,
            "dataset": row["dataset"],
            "problem_id": row["problem_id"],
            "language": row["language"],
            "source": "Human",
            "provider": "",
            "model": "",
            "model_group": "human",
            "prompt_type": "original",
            "run": i,
            "correctness": "accepted_by_dataset",
            "original_file": str(src),
        }

        metadata_file_for(dst).write_text(
            json.dumps(meta, indent=2, ensure_ascii=False),
            encoding="utf-8",
        )


# ============================================================
# Experiments
# ============================================================

def run_experiment_1(
    manifest: Path,
    providers: List[str],
    models: Dict[str, Optional[str]],
    repeats: int,
    force: bool,
):
    """
    Human vs AI.
    """
    rows = read_jsonl(manifest)

    for row in rows:
        stage_existing_row(1, row, include_human=True, include_ai=False)
        prompt = standard_prompt(row)

        for provider in providers:
            for run_no in range(1, repeats + 1):
                model = models.get(provider)
                group = model or "default"
                out = sample_file(1, row, provider, group, "standard", run_no)

                if out.exists() and not force:
                    print(f"SKIP {out}")
                    continue

                print(f"EXP1 {row['dataset']} {row['problem_id']} {row['language']} -> {provider}")
                code = provider_generate(provider, prompt, model)

                save_sample(out, code, {
                    "experiment": 1,
                    "dataset": row["dataset"],
                    "problem_id": row["problem_id"],
                    "language": row["language"],
                    "source": "AI",
                    "provider": provider,
                    "model": model or "",
                    "model_group": group,
                    "prompt_type": "standard",
                    "run": run_no,
                    "correctness": "not_tested",
                })


def run_experiment_2(
    manifest: Path,
    providers: List[str],
    models: Dict[str, Optional[str]],
    repeats: int,
    languages: List[str],
    force: bool,
):
    """
    Different programming languages.
    """
    allowed = {normalise_language(x) for x in languages}
    rows = [r for r in read_jsonl(manifest) if normalise_language(r["language"]) in allowed]

    for row in rows:
        stage_existing_row(2, row, include_human=True, include_ai=False)
        prompt = standard_prompt(row)

        for provider in providers:
            for run_no in range(1, repeats + 1):
                model = models.get(provider)
                group = model or "default"
                out = sample_file(2, row, provider, group, "standard", run_no)

                if out.exists() and not force:
                    print(f"SKIP {out}")
                    continue

                print(f"EXP2 {row['problem_id']} {row['language']} -> {provider}")
                code = provider_generate(provider, prompt, model)

                save_sample(out, code, {
                    "experiment": 2,
                    "dataset": row["dataset"],
                    "problem_id": row["problem_id"],
                    "language": row["language"],
                    "source": "AI",
                    "provider": provider,
                    "model": model or "",
                    "model_group": group,
                    "prompt_type": "standard",
                    "run": run_no,
                    "correctness": "not_tested",
                })


def run_experiment_3(
    manifest: Path,
    provider: str,
    old_model: str,
    new_model: str,
    repeats: int,
    force: bool,
):
    """
    Older vs newer model of the same provider.

    The caller is responsible for selecting and validating the model pair.
    The exact model ID is included in the folder name so a future rerun with
    a different pair cannot accidentally SKIP an old sample.
    """
    rows = read_jsonl(manifest)

    for row in rows:
        prompt = standard_prompt(row)

        for group, model in [("older", old_model), ("newer", new_model)]:
            storage_group = f"{group}_{safe_name(model)}"

            for run_no in range(1, repeats + 1):
                out = sample_file(
                    3,
                    row,
                    provider,
                    storage_group,
                    "standard",
                    run_no,
                )

                if out.exists() and not force:
                    print(f"SKIP {out}")
                    continue

                print(
                    f"EXP3 {row['problem_id']} {row['language']} "
                    f"{provider} {group}:{model}"
                )

                try:
                    code = provider_generate(provider, prompt, model)
                except subprocess.TimeoutExpired as exc:
                    raise RuntimeError(
                        f"{provider} timed out after "
                        f"{AI_GENERATION_TIMEOUT_SECONDS} seconds while using {model}."
                    ) from exc

                save_sample(out, code, {
                    "experiment": 3,
                    "dataset": row["dataset"],
                    "problem_id": row["problem_id"],
                    "language": row["language"],
                    "source": "AI",
                    "provider": provider,
                    "model": model,
                    "model_group": group,
                    "prompt_type": "standard",
                    "run": run_no,
                    "correctness": "not_tested",
                })


def run_experiment_4(
    manifest: Path,
    providers: List[str],
    models: Dict[str, Optional[str]],
    repeats: int,
    force: bool,
):
    """
    Standard prompt vs maintainability-focused prompt.
    """
    rows = read_jsonl(manifest)

    for row in rows:
        prompts = {
            "standard": standard_prompt(row),
            "maintainability": maintainability_prompt(row),
        }

        for provider in providers:
            model = models.get(provider)
            group = model or "default"

            for prompt_type, prompt in prompts.items():
                for run_no in range(1, repeats + 1):
                    out = sample_file(4, row, provider, group, prompt_type, run_no)

                    if out.exists() and not force:
                        print(f"SKIP {out}")
                        continue

                    print(
                        f"EXP4 {row['problem_id']} {row['language']} "
                        f"{provider} {prompt_type}"
                    )

                    code = provider_generate(provider, prompt, model)

                    save_sample(out, code, {
                        "experiment": 4,
                        "dataset": row["dataset"],
                        "problem_id": row["problem_id"],
                        "language": row["language"],
                        "source": "AI",
                        "provider": provider,
                        "model": model or "",
                        "model_group": group,
                        "prompt_type": prompt_type,
                        "run": run_no,
                        "correctness": "not_tested",
                    })


# ============================================================
# Claude batch export/import
# ============================================================

def prompts_for_experiment(experiment: int, row: dict) -> List[tuple]:
    if experiment in (1, 2, 3):
        return [("standard", standard_prompt(row))]
    if experiment == 4:
        return [
            ("standard", standard_prompt(row)),
            ("maintainability", maintainability_prompt(row)),
        ]
    raise ValueError("Experiment must be 1, 2, 3, or 4")


def export_claude_batch(
    experiment: int,
    manifest: Path,
    output: Path,
    repeats: int,
    languages: Optional[List[str]],
    batch_size: int = 25,
):
    """
    Export prompts for MANUAL use in the Claude website.

    No Claude API or Claude Code generation is performed. When batch_size > 0,
    prompts are split into manageable numbered .txt files with matching .jsonl
    request maps. Set batch_size=0 to create one legacy single batch.
    """
    rows = read_jsonl(manifest)
    allowed = None if not languages else {normalise_language(x) for x in languages}

    records = []
    counter = 1
    for row in rows:
        if allowed and normalise_language(row["language"]) not in allowed:
            continue
        for prompt_type, prompt in prompts_for_experiment(experiment, row):
            for run_no in range(1, repeats + 1):
                request_id = f"REQ{counter:06d}"
                counter += 1
                records.append({
                    "request_id": request_id,
                    "experiment": experiment,
                    "dataset": row["dataset"],
                    "problem_id": row["problem_id"],
                    "language": row["language"],
                    "prompt_type": prompt_type,
                    "run": run_no,
                    "prompt": prompt,
                })

    if not records:
        raise RuntimeError("No Claude requests were produced from the supplied manifest.")

    if batch_size < 0:
        raise ValueError("--batch-size must be 0 or greater")

    chunks = [records] if batch_size == 0 else [
        records[i:i + batch_size] for i in range(0, len(records), batch_size)
    ]

    created = []
    multi = len(chunks) > 1 or batch_size > 0
    for batch_no, chunk in enumerate(chunks, start=1):
        base = output
        if multi:
            base = output.parent / f"{output.name}_batch_{batch_no:03d}"

        map_path = base.with_suffix(".jsonl")
        txt_path = base.with_suffix(".txt")
        write_jsonl(map_path, chunk)

        with txt_path.open("w", encoding="utf-8") as f:
            f.write(
                "For each request below, return ONLY the requested source code.\n"
                "Keep the request markers exactly as written.\n\n"
                "Required response format:\n"
                "=== REQ000001 START ===\n"
                "[source code]\n"
                "=== REQ000001 END ===\n\n"
            )
            for rec in chunk:
                f.write(f"=== {rec['request_id']} PROMPT ===\n")
                f.write(rec["prompt"])
                f.write("\n\n")

        created.append((txt_path, map_path))
        print(f"Claude prompt batch: {txt_path}")
        print(f"Claude request map:  {map_path}")

    print(f"Claude requests exported: {len(records)} across {len(created)} batch(es).")
    print("Claude remains manual-only: paste each .txt file into the Claude website.")
    return created


def import_claude_batch(
    request_map: Path,
    response_file: Path,
    model_label: str,
    force: bool,
):
    requests = {r["request_id"]: r for r in read_jsonl(request_map)}
    response = response_file.read_text(encoding="utf-8", errors="ignore")

    pattern = re.compile(
        r"===\s*(REQ\d+)\s+START\s*===\s*(.*?)\s*===\s*\1\s+END\s*===",
        re.S,
    )

    imported = 0
    for match in pattern.finditer(response):
        req_id = match.group(1)
        code = clean_code(match.group(2))
        if req_id not in requests:
            continue

        rec = requests[req_id]
        row = {
            "dataset": rec["dataset"],
            "problem_id": rec["problem_id"],
            "language": rec["language"],
        }
        out = sample_file(
            rec["experiment"], row, "claude", model_label,
            rec["prompt_type"], rec["run"],
        )
        if out.exists() and not force:
            continue

        save_sample(out, code, {
            "experiment": rec["experiment"],
            "dataset": rec["dataset"],
            "problem_id": rec["problem_id"],
            "language": rec["language"],
            "source": "AI",
            "provider": "claude",
            "model": model_label,
            "model_group": model_label,
            "prompt_type": rec["prompt_type"],
            "run": rec["run"],
            "correctness": "not_tested",
        })
        imported += 1

    print(f"Imported {imported} Claude outputs from {response_file}.")
    return imported


def import_claude_batches(
    batch_prefix: Path,
    model_label: str,
    force: bool,
):
    """Import every numbered Claude batch response matching a prefix."""
    pattern = f"{batch_prefix.name}_batch_*.jsonl"
    request_maps = sorted(batch_prefix.parent.glob(pattern))
    if not request_maps:
        raise FileNotFoundError(
            f"No Claude request maps matched {batch_prefix.parent / pattern}"
        )

    imported_total = 0
    missing = []
    for request_map in request_maps:
        response = request_map.with_name(request_map.stem + "_response.txt")
        if not response.exists():
            missing.append(response)
            continue
        imported_total += import_claude_batch(
            request_map, response, model_label, force
        )

    if missing:
        print("\nMissing Claude response files:")
        for path in missing:
            print(f"  {path}")
        raise FileNotFoundError(
            f"{len(missing)} Claude response file(s) are still missing."
        )

    print(f"\nBulk Claude import complete: {imported_total} outputs imported.")


# ============================================================
# Metric calculation - Lizard only
# ============================================================

def lizard_available() -> bool:
    """Return True only when Lizard and its Halstead extension are importable."""
    try:
        import lizard  # noqa: F401
        from lizard_ext import lizardhalstead  # noqa: F401
        from lizard_ext import lizardoutside  # noqa: F401
        return True
    except Exception:
        return False


def _empty_metric_result() -> dict:
    return {
        "metric_language": "",
        "mi": "",
        "cc": "",
        "loc": "",
        "halstead_volume": "",
        "halstead_difficulty": "",
        "halstead_effort": "",
        "comment_ratio": "",
        "operators_total": "",
        "operators_unique": "",
        "operands_total": "",
        "operands_unique": "",
    }


def _metric_language_for_file(code_file: Path) -> str:
    mapping = {
        ".py": "Python",
        ".java": "Java",
        ".cpp": "C++",
        ".cc": "C++",
        ".cxx": "C++",
        ".c": "C",
        ".cs": "C#",
        ".js": "JavaScript",
        ".ts": "TypeScript",
        ".go": "Go",
        ".rs": "Rust",
        ".rb": "Ruby",
        ".php": "PHP",
    }
    return mapping.get(code_file.suffix.lower(), code_file.suffix.lstrip("."))


def _python_comment_lines(source: str) -> int:
    """Count physical Python lines containing # comments using tokenize."""
    import io
    import token
    import tokenize

    lines = set()
    try:
        for tok in tokenize.generate_tokens(io.StringIO(source).readline):
            if tok.type == token.COMMENT:
                for line_no in range(tok.start[0], tok.end[0] + 1):
                    lines.add(line_no)
    except (tokenize.TokenError, IndentationError):
        # Generated code may be incomplete. Fall back to conservative line counting.
        for line_no, line in enumerate(source.splitlines(), start=1):
            if line.lstrip().startswith("#"):
                lines.add(line_no)
    return len(lines)


def _c_like_comment_lines(source: str) -> int:
    """
    Count physical lines containing // or /* ... */ comments while ignoring
    comment-looking text inside quoted strings.
    """
    comment_lines = set()
    in_block = False

    for line_no, line in enumerate(source.splitlines(), start=1):
        i = 0
        quote = None
        escaped = False
        has_comment = False

        while i < len(line):
            if in_block:
                has_comment = True
                end = line.find("*/", i)
                if end == -1:
                    i = len(line)
                    break
                in_block = False
                i = end + 2
                continue

            ch = line[i]
            nxt = line[i + 1] if i + 1 < len(line) else ""

            if quote is not None:
                if escaped:
                    escaped = False
                elif ch == "\\":
                    escaped = True
                elif ch == quote:
                    quote = None
                i += 1
                continue

            if ch in {'"', "'", "`"}:
                quote = ch
                i += 1
                continue

            if ch == "/" and nxt == "/":
                has_comment = True
                break

            if ch == "/" and nxt == "*":
                has_comment = True
                in_block = True
                i += 2
                continue

            i += 1

        if has_comment:
            comment_lines.add(line_no)

    return len(comment_lines)


def _comment_line_count(code_file: Path, source: str) -> int:
    suffix = code_file.suffix.lower()
    if suffix == ".py":
        return _python_comment_lines(source)
    if suffix in {".java", ".cpp", ".cc", ".cxx", ".c", ".cs", ".js", ".ts", ".go", ".rs", ".php"}:
        return _c_like_comment_lines(source)

    # Conservative fallback for languages with # and/or // comments.
    count = 0
    for line in source.splitlines():
        stripped = line.lstrip()
        if stripped.startswith("#") or stripped.startswith("//"):
            count += 1
    return count


def _maintainability_index(volume: float, cc: float, loc: float, comment_ratio: float) -> float:
    """
    Calculate a 0-100 Maintainability Index using the SEI/Radon-style formula.

    Inputs are all derived consistently from the same source file:
      - HV: Lizard Halstead token classification
      - CC: Lizard CCN
      - LOC: Lizard NLOC
      - comment_ratio: percentage of non-blank code/comment lines that are comments
    """
    if volume <= 0 or loc <= 0:
        return 100.0

    # The comment term convention uses the comment percentage in radians,
    # matching the common SEI/Radon form before normalising to 0-100.
    comment_term = 50.0 * math.sin(
        math.sqrt(2.4 * math.radians(max(0.0, comment_ratio)))
    )

    raw = (
        171.0
        - 5.2 * math.log(volume)
        - 0.23 * cc
        - 16.2 * math.log(loc)
        + comment_term
    )

    return max(0.0, min(100.0, raw * 100.0 / 171.0))


def analyse_with_lizard(code_file: Path) -> dict:
    """
    Analyse one source file entirely through Lizard's parsing/token pipeline.

    - NLOC and CCN come from Lizard.
    - Halstead operator/operand counts come from Lizard's -Ehalstead extension.
    - -Eoutside includes top-level/global code as a pseudo-function, which is
      important for script-style Python solutions.
    - MI is calculated locally from the Lizard-derived HV/CC/NLOC values.
    """
    try:
        import lizard
    except ImportError as exc:
        raise RuntimeError(
            "Lizard is not installed. Run: python -m pip install --upgrade lizard"
        ) from exc

    try:
        extensions = lizard.get_extensions(["outside", "halstead"])
        analyzer = lizard.FileAnalyzer(extensions)
    except Exception as exc:
        raise RuntimeError(
            "Your installed Lizard does not provide the required Halstead extension. "
            "Run: python -m pip install --upgrade lizard"
        ) from exc

    source = code_file.read_text(encoding="utf-8", errors="replace")
    info = analyzer.analyze_source_code(str(code_file), source)

    # File-level Lizard metrics. With -Eoutside, global/top-level code is also
    # present in function_list and therefore contributes to CC/Halstead totals.
    loc = int(getattr(info, "nloc", 0) or 0)
    cc = float(getattr(info, "CCN", 0) or 0)

    operators = Counter()
    operands = Counter()

    for function in getattr(info, "function_list", []) or []:
        operators.update(getattr(function, "_halstead_operators", {}) or {})
        operands.update(getattr(function, "_halstead_operands", {}) or {})

    n1 = len(operators)
    n2 = len(operands)
    N1 = sum(operators.values())
    N2 = sum(operands.values())
    vocabulary = n1 + n2
    length = N1 + N2

    volume = (
        float(length) * math.log2(float(vocabulary))
        if length > 0 and vocabulary > 0
        else 0.0
    )
    difficulty = (
        (float(n1) / 2.0) * (float(N2) / float(n2))
        if n1 > 0 and n2 > 0
        else 0.0
    )
    effort = difficulty * volume

    comment_lines = _comment_line_count(code_file, source)
    denominator = loc + comment_lines
    comment_ratio = (
        (100.0 * comment_lines / denominator)
        if denominator > 0
        else 0.0
    )

    mi = _maintainability_index(volume, cc, loc, comment_ratio)

    return {
        "metric_language": _metric_language_for_file(code_file),
        "mi": round(mi, 2),
        "cc": round(cc, 2),
        "loc": int(loc),
        "halstead_volume": round(volume, 2),
        "halstead_difficulty": round(difficulty, 2),
        "halstead_effort": round(effort, 2),
        "comment_ratio": round(comment_ratio, 2),
        "operators_total": int(N1),
        "operators_unique": int(n1),
        "operands_total": int(N2),
        "operands_unique": int(n2),
    }


def iter_code_files(root: Path) -> Iterable[Path]:
    valid_exts = set(LANGUAGE_EXTENSIONS.values())
    for path in root.rglob("*"):
        if path.is_file() and path.suffix in valid_exts:
            yield path


def analyse_experiment(experiment: int, output_csv: Path, manifest: Optional[Path] = None):
    root = SAMPLE_DIR / f"experiment_{experiment}"

    if not root.exists():
        raise FileNotFoundError(f"No samples found at {root}")

    if not lizard_available():
        raise RuntimeError(
            "Lizard with the Halstead extension is not installed.\n"
            "Install/update it with: python -m pip install --upgrade lizard"
        )

    code_files = sorted(iter_code_files(root))

    allowed_keys = None
    if manifest is not None:
        allowed_keys = {
            (str(r.get("dataset", "")), str(r.get("problem_id", "")), normalise_language(str(r.get("language", ""))))
            for r in read_jsonl(manifest)
        }

    filtered_files = []
    excluded_copilot = 0
    excluded_outside_manifest = 0
    for code_file in code_files:
        meta_path = metadata_file_for(code_file)
        meta = json.loads(meta_path.read_text(encoding="utf-8")) if meta_path.exists() else {}
        if str(meta.get("provider", "")).lower() == "copilot":
            excluded_copilot += 1
            continue
        if allowed_keys is not None:
            key = (
                str(meta.get("dataset", "")),
                str(meta.get("problem_id", "")),
                normalise_language(str(meta.get("language", ""))),
            )
            if key not in allowed_keys:
                excluded_outside_manifest += 1
                continue
        filtered_files.append(code_file)

    code_files = filtered_files
    rows = []

    print(f"\nMetric analysis: {len(code_files)} files (Lizard)")
    if excluded_copilot:
        print(f"Excluded legacy Copilot samples: {excluded_copilot}")
    if excluded_outside_manifest:
        print(f"Excluded samples outside study manifest: {excluded_outside_manifest}")

    for index, code_file in enumerate(code_files, start=1):
        meta_path = metadata_file_for(code_file)

        if meta_path.exists():
            meta = json.loads(meta_path.read_text(encoding="utf-8"))
        else:
            meta = {}

        print(f"ANALYSE [{index}/{len(code_files)}] {code_file}", flush=True)

        try:
            metrics = analyse_with_lizard(code_file)
            metric_error = ""
            print(
                f"  OK MI={metrics['mi']} CC={metrics['cc']} "
                f"LOC={metrics['loc']} HV={metrics['halstead_volume']}",
                flush=True,
            )
        except Exception as exc:
            metrics = _empty_metric_result()
            metric_error = str(exc)
            print(f"  METRIC ERROR - {metric_error}", flush=True)
            print("  Continuing to the next file...", flush=True)

        rows.append({
            "experiment": meta.get("experiment", experiment),
            "dataset": meta.get("dataset", ""),
            "problem_id": meta.get("problem_id", ""),
            "language": meta.get("language", ""),
            "source": meta.get("source", ""),
            "provider": meta.get("provider", ""),
            "model": meta.get("model", ""),
            "model_group": meta.get("model_group", ""),
            "prompt_type": meta.get("prompt_type", ""),
            "run": meta.get("run", ""),
            "correctness": meta.get("correctness", "not_tested"),
            "difficulty": meta.get("difficulty", ""),
            "scenario": meta.get("scenario", ""),
            "generator_type": meta.get("generator_type", ""),
            "file": str(code_file),
            **metrics,
            "metric_error": metric_error,
        })

    fieldnames = [
        "experiment",
        "dataset",
        "problem_id",
        "language",
        "source",
        "provider",
        "model",
        "model_group",
        "prompt_type",
        "run",
        "correctness",
        "difficulty",
        "scenario",
        "generator_type",
        "file",
        "metric_language",
        "mi",
        "cc",
        "loc",
        "halstead_volume",
        "halstead_difficulty",
        "halstead_effort",
        "comment_ratio",
        "operators_total",
        "operators_unique",
        "operands_total",
        "operands_unique",
        "metric_error",
    ]

    output_csv.parent.mkdir(parents=True, exist_ok=True)

    with output_csv.open("w", newline="", encoding="utf-8") as f:
        writer = csv.DictWriter(f, fieldnames=fieldnames)
        writer.writeheader()
        writer.writerows(rows)

    failures = sum(1 for row in rows if row["metric_error"])
    print(f"Wrote {len(rows)} metric rows -> {output_csv}")
    if failures:
        print(
            f"WARNING: {failures} file(s) could not be measured. "
            "See the metric_error column in the CSV."
        )

# ============================================================
# CLI argument parsing
# ============================================================

def parse_provider_models(values: List[str]) -> Dict[str, Optional[str]]:
    """
    --provider-model codex:gpt-x
    """
    out: Dict[str, Optional[str]] = {}

    for value in values:
        if ":" in value:
            provider, model = value.split(":", 1)
            out[provider.lower()] = None if model.lower() in ("", "auto", "default") else model
        else:
            out[value.lower()] = None

    return out



# ============================================================
# Simple runner
# ============================================================

DEFAULT_DATASET_DIR = Path("datasets")

DEFAULT_HVAI_PYTHON = DEFAULT_DATASET_DIR / "human_vs_ai_quality" / "python_dataset.jsonl"
DEFAULT_HVAI_JAVA = DEFAULT_DATASET_DIR / "human_vs_ai_quality" / "java_dataset.jsonl"

DEFAULT_BACK3474_DIR = DEFAULT_DATASET_DIR / "back3474"
DEFAULT_BACK3474_FILE = (
    DEFAULT_BACK3474_DIR / "AI-Human-Generated-Program-Code-Dataset.jsonl"
)

DEFAULT_CODENET_DIR = DEFAULT_DATASET_DIR / "Project_CodeNet"

SIMPLE_COMMANDS = {
    "quick",
    "full",
    "back3474",
    "codenet",
    "exp1",
    "exp2",
    "exp3",
    "exp4",
    "claude-export",
    "claude-import",
    "results",
    "tools",
    "menu",
}


def _yes_no(question: str, default: bool = True) -> bool:
    suffix = " [Y/n]: " if default else " [y/N]: "
    answer = input(question + suffix).strip().lower()
    if not answer:
        return default
    return answer in {"y", "yes"}


def _ensure_lizard_simple() -> bool:
    if lizard_available():
        return True

    print(
        "\n'Lizard' (with its Halstead extension) is required to calculate "
        "MI, CC, LOC and Halstead metrics."
    )

    if not sys.stdin.isatty():
        print("Install/update it with: python -m pip install --upgrade lizard")
        return False

    if not _yes_no("Install/update Lizard now?", True):
        return False

    result = subprocess.run(
        [sys.executable, "-m", "pip", "install", "--upgrade", "lizard"]
    )
    return result.returncode == 0 and lizard_available()

def _remove_if_exists(path: Path):
    if path.exists():
        path.unlink()


def _find_hvai_files():
    py = DEFAULT_HVAI_PYTHON if DEFAULT_HVAI_PYTHON.exists() else None
    java = DEFAULT_HVAI_JAVA if DEFAULT_HVAI_JAVA.exists() else None

    if py or java:
        return py, java

    # Fall back to a small automatic search inside datasets/.
    candidates = list(DEFAULT_DATASET_DIR.rglob("*.jsonl")) if DEFAULT_DATASET_DIR.exists() else []
    for candidate in candidates:
        name = candidate.name.lower()
        if py is None and "python" in name and "dataset" in name:
            py = candidate
        if java is None and "java" in name and "dataset" in name:
            java = candidate

    return py, java


def _require_hvai_files():
    py, java = _find_hvai_files()
    if not py and not java:
        print(
            "\nHuman-vs-AI dataset files were not found.\n"
            "Put the downloaded files here:\n"
            f"  {DEFAULT_HVAI_PYTHON}\n"
            f"  {DEFAULT_HVAI_JAVA}\n"
        )
        return None, None
    return py, java


def _simple_hvai(max_records: int, output_name: str):
    if not _ensure_lizard_simple():
        return

    py, java = _require_hvai_files()
    if not py and not java:
        return

    output = RESULT_DIR / output_name
    summary = RESULT_DIR / output_name.replace(".csv", "_summary.csv")

    # Simple mode always creates a clean result instead of appending duplicates.
    _remove_if_exists(output)
    _remove_if_exists(summary)

    print("\nAnalysing Human vs ChatGPT...")
    analyse_hvai_quality(
        py,
        java,
        output,
        max_records,
        250,
        ["Human", "ChatGPT"],
    )

    summarise_results(
        output,
        summary,
        ["language", "source", "model"],
    )

    print("\nDONE")
    print(f"Detailed results: {output}")
    print(f"Summary:          {summary}")


def simple_quick():
    print("\n=== QUICK TRIAL ===")
    print("Runs 100 records per available language using Human vs ChatGPT.")
    _simple_hvai(100, "quick_trial.csv")


def simple_full():
    print("\n=== FULL HUMAN VS CHATGPT ANALYSIS ===")
    print("Uses all available records in the downloaded Python/Java files.")
    if sys.stdin.isatty() and not _yes_no("Run the full dataset?", False):
        print("Cancelled.")
        return
    _simple_hvai(0, "human_vs_chatgpt_full.csv")


def _clone_back3474_if_needed() -> bool:
    if DEFAULT_BACK3474_FILE.exists():
        return True

    print("\nBack3474 dataset was not found.")
    if not sys.stdin.isatty():
        print(
            "Clone it with:\n"
            "git clone https://github.com/Back3474/"
            "AI-Human-Generated-Program-Code-Dataset.git datasets/back3474"
        )
        return False

    if not _yes_no("Clone the public Back3474 dataset now?", True):
        return False

    DEFAULT_DATASET_DIR.mkdir(parents=True, exist_ok=True)

    result = subprocess.run([
        "git",
        "clone",
        "https://github.com/Back3474/AI-Human-Generated-Program-Code-Dataset.git",
        str(DEFAULT_BACK3474_DIR),
    ])

    if result.returncode != 0:
        print("Git clone failed. Check that Git is installed and try again.")
        return False

    return DEFAULT_BACK3474_FILE.exists()


def simple_back3474():
    print("\n=== BACK3474 ANALYSIS ===")

    if not _ensure_lizard_simple():
        return

    if not _clone_back3474_if_needed():
        return

    manifest = MANIFEST_DIR / "back3474.jsonl"
    output = RESULT_DIR / "back3474_experiment1.csv"
    summary = RESULT_DIR / "back3474_experiment1_summary.csv"

    print("\n1/4 Preparing dataset...")
    prepare_back3474(
        DEFAULT_BACK3474_FILE,
        manifest,
        WORK_DIR / "extracted",
        ["Python", "Java", "C++"],
    )

    print("\n2/4 Dataset contents...")
    inspect_manifest(manifest)

    print("\n3/4 Staging existing human/AI samples...")
    stage_existing_manifest(
        manifest,
        1,
        ["Python", "Java", "C++"],
        ["Back3474"],
    )

    print("\n4/4 Calculating metrics...")
    _remove_if_exists(output)
    _remove_if_exists(summary)
    analyse_experiment(1, output)
    summarise_results(
        output,
        summary,
        ["language", "source", "provider", "model"],
    )

    print("\nDONE")
    print(f"Detailed results: {output}")
    print(f"Summary:          {summary}")


def simple_codenet():
    print("\n=== PREPARE CODENET ===")

    root = DEFAULT_CODENET_DIR
    if not root.exists():
        if not sys.stdin.isatty():
            print(f"Project CodeNet was not found at: {root}")
            return
        entered = input(
            f"CodeNet folder [{root}]: "
        ).strip()
        if entered:
            root = Path(entered)

    if not root.exists():
        print(f"Folder not found: {root}")
        return

    output = MANIFEST_DIR / "codenet.jsonl"

    prepare_codenet(
        root,
        output,
        ["Python", "Java", "C++", "C#"],
        DEFAULT_HUMAN_PER_PROBLEM,
        0,
        DEFAULT_SEED,
    )

    inspect_manifest(output)
    print(f"\nCodeNet is ready: {output}")


def _available_problem_manifests() -> List[Path]:
    preferred = [
        MANIFEST_DIR / "codenet.jsonl",
        MANIFEST_DIR / "multipl_e.jsonl",
        MANIFEST_DIR / "codecontests.jsonl",
        MANIFEST_DIR / "back3474.jsonl",
    ]
    return [p for p in preferred if p.exists()]


def _build_auto_manifest() -> Optional[Path]:
    study = MANIFEST_DIR / "study_sample.jsonl"
    if study.exists():
        return study

    manifests = _available_problem_manifests()

    if not manifests:
        print(
            "\nNo prepared problem manifests were found.\n"
            "Prepare CodeNet, Back3474, MultiPL-E or CodeContests first."
        )
        return None

    if len(manifests) == 1:
        return manifests[0]

    output = MANIFEST_DIR / "auto_all_datasets.jsonl"
    merge_manifests(manifests, output)
    return output


def _available_cli_providers() -> List[str]:
    return ["codex"] if resolve_cli("codex") else []


def _choose_providers() -> List[str]:
    available = _available_cli_providers()
    if not available:
        print(
            "\nCodex CLI was not found.\n"
            "Run: python New_Thesis_B_Script.py tools\n"
            "Claude remains available through manual batch export/import."
        )
        return []
    print("Using automatic provider: codex")
    return ["codex"]


def _run_simple_ai_experiment(experiment: int):
    manifest = _build_auto_manifest()
    if not manifest:
        return

    providers = _choose_providers()
    if not providers:
        return

    model_map = {provider: None for provider in providers}

    print(f"\n=== EXPERIMENT {experiment} ===")
    print(f"Manifest: {manifest}")
    print(f"Tools:    {', '.join(providers)}")

    if experiment == 1:
        run_experiment_1(manifest, providers, model_map, 1, False)
    elif experiment == 2:
        run_experiment_2(
            manifest,
            providers,
            model_map,
            1,
            ["Python", "Java", "C++", "C#"],
            False,
        )
    elif experiment == 4:
        run_experiment_4(manifest, providers, model_map, 1, False)
    else:
        raise ValueError("Simple runner supports Experiment 1, 2 or 4 here.")

    if not _ensure_lizard_simple():
        return

    output = RESULT_DIR / f"experiment_{experiment}.csv"
    summary = RESULT_DIR / f"experiment_{experiment}_summary.csv"

    _remove_if_exists(output)
    _remove_if_exists(summary)

    analyse_experiment(experiment, output, manifest)

    group_by = ["dataset", "language", "source", "provider", "model"]
    if experiment == 4:
        group_by.append("prompt_type")

    summarise_results(output, summary, group_by)

    print("\nDONE")
    print(f"Detailed results: {output}")
    print(f"Summary:          {summary}")


def simple_exp1():
    _run_simple_ai_experiment(1)


def simple_exp2():
    _run_simple_ai_experiment(2)


def simple_exp4():
    _run_simple_ai_experiment(4)


# ============================================================
# Experiment 3 automatic model discovery
# ============================================================

def _extract_model_strings(value) -> List[str]:
    """Recursively extract strings from JSON-like data."""
    found: List[str] = []

    if isinstance(value, dict):
        for key, item in value.items():
            if isinstance(key, str):
                found.append(key)
            found.extend(_extract_model_strings(item))
    elif isinstance(value, list):
        for item in value:
            found.extend(_extract_model_strings(item))
    elif isinstance(value, str):
        found.append(value)

    return found


def _gpt_generation(model: str) -> Optional[tuple]:
    match = re.match(r"^gpt-(\d+)\.(\d+)", model.lower())
    if not match:
        return None
    return int(match.group(1)), int(match.group(2))


def _codex_variant_priority(model: str) -> tuple:
    """Prefer the full/default model within a numbered GPT generation."""
    lower = model.lower()
    generation = _gpt_generation(lower)
    canonical = f"gpt-{generation[0]}.{generation[1]}" if generation else ""

    if lower == canonical:
        rank = 0
    elif lower.endswith("-sol"):
        rank = 1
    elif "codex" in lower and "spark" not in lower:
        rank = 2
    elif lower.endswith("-terra"):
        rank = 3
    elif lower.endswith("-luna"):
        rank = 4
    elif "mini" in lower:
        rank = 5
    elif "spark" in lower:
        rank = 6
    else:
        rank = 7

    return rank, len(lower), lower


def _probe_codex_model(model: str) -> bool:
    cmd = cli_prefix("codex") + [
        "exec",
        "--ephemeral",
        "--skip-git-repo-check",
        "--model",
        model,
        "Reply with exactly OK.",
    ]

    try:
        result = run_process(cmd, timeout=MODEL_PROBE_TIMEOUT_SECONDS)
    except subprocess.TimeoutExpired:
        return False

    return result.returncode == 0


def _discover_codex_pair() -> tuple:
    """
    Read the model catalogue exposed by the installed Codex CLI, then choose
    one runnable model from the newest GPT generation and one from the previous
    runnable GPT generation.
    """
    print("  Reading Codex model catalogue...", flush=True)

    try:
        result = run_process(
            cli_prefix("codex") + ["debug", "models"],
            timeout=30,
        )
    except subprocess.TimeoutExpired as exc:
        raise RuntimeError("Codex model discovery timed out.") from exc

    if result.returncode != 0:
        raise RuntimeError(
            "Could not read the Codex model catalogue. "
            "Update Codex CLI with: npm install -g @openai/codex@latest\n"
            + (result.stderr or result.stdout or "")
        )

    raw = (result.stdout or "") + "\n" + (result.stderr or "")
    strings: List[str] = []

    try:
        payload = json.loads(raw)
        strings.extend(_extract_model_strings(payload))
    except json.JSONDecodeError:
        strings.append(raw)

    models = set()
    for item in strings:
        models.update(
            re.findall(
                r"\bgpt-\d+\.\d+(?:-[a-z0-9][a-z0-9.-]*)?\b",
                item.lower(),
            )
        )

    # Defensive fallback: the raw debug output can change shape while the
    # command is experimental, so also scan it directly.
    models.update(
        re.findall(
            r"\bgpt-\d+\.\d+(?:-[a-z0-9][a-z0-9.-]*)?\b",
            raw.lower(),
        )
    )

    by_generation: Dict[tuple, List[str]] = {}
    for model in models:
        generation = _gpt_generation(model)
        if generation is not None:
            by_generation.setdefault(generation, []).append(model)

    if len(by_generation) < 2:
        raise RuntimeError(
            "Codex did not expose two numbered GPT generations. "
            "Experiment 3 needs a genuine older and newer model."
        )

    runnable: List[tuple] = []

    for generation in sorted(by_generation, reverse=True):
        candidates = sorted(
            set(by_generation[generation]),
            key=_codex_variant_priority,
        )

        for model in candidates:
            print(f"    testing {model}...", flush=True)
            if _probe_codex_model(model):
                runnable.append((generation, model))
                print("      OK", flush=True)
                break
            print("      unavailable", flush=True)

        if len(runnable) >= 2:
            break

    if len(runnable) < 2:
        raise RuntimeError(
            "Your current ChatGPT/Codex account does not expose two runnable "
            "GPT generations, so a valid old/new comparison cannot be made."
        )

    newer = runnable[0][1]
    older = runnable[1][1]
    return older, newer



def auto_exp3_models(provider: str) -> tuple:
    provider = provider.lower()

    if provider == "codex":
        return _discover_codex_pair()

    raise ValueError(f"No automatic model discovery for provider: {provider}")


def _save_exp3_model_selection(selections: dict) -> Path:
    from datetime import datetime, timezone

    path = WORK_DIR / "experiment_3_model_selection.json"
    payload = {
        "selected_at_utc": datetime.now(timezone.utc).isoformat(),
        "experiment": 3,
        "selection_method": (
            "Models were discovered from the installed provider CLI and then "
            "validated with a live non-interactive request before generation."
        ),
        "providers": selections,
    }

    path.write_text(
        json.dumps(payload, indent=2, ensure_ascii=False),
        encoding="utf-8",
    )
    return path


def _export_exp3_claude_website_batch(manifest: Path) -> tuple:
    """
    Claude is intentionally not called through Claude Code for Experiment 3.
    Export one identical prompt batch so the user can paste it into the Claude
    website once with an older model and once with the current/newer model.
    """
    batch_base = CLAUDE_DIR / "experiment_3_claude"
    export_claude_batch(3, manifest, batch_base, 1, None)

    instruction_file = CLAUDE_DIR / "experiment_3_CLAUDE_INSTRUCTIONS.txt"
    instruction_file.write_text(
        "EXPERIMENT 3 - CLAUDE WEBSITE: OLDER VS NEWER MODEL\n\n"
        "1. Open experiment_3_claude.txt.\n"
        "2. In the Claude website, select an OLDER model version.\n"
        "3. Paste the entire batch and save the response as:\n"
        "   experiment_3_claude_older_response.txt\n"
        "4. Start a fresh Claude chat and select the CURRENT/NEWER model.\n"
        "5. Paste the EXACT SAME batch and save the response as:\n"
        "   experiment_3_claude_newer_response.txt\n"
        "6. Record the exact model names shown by Claude for thesis reporting.\n"
        "7. Import the responses later with the Claude import workflow.\n\n"
        "Important: do not ask a newer model to imitate an older model. Both "
        "runs must use genuinely selectable model versions in the Claude website.\n",
        encoding="utf-8",
    )

    return batch_base.with_suffix(".txt"), instruction_file


def simple_exp3():
    print("\n=== EXPERIMENT 3: OLDER VS NEWER MODEL ===")

    manifest = _build_auto_manifest()
    if not manifest:
        return

    selections = {}
    if resolve_cli("codex"):
        try:
            old_model, new_model = auto_exp3_models("codex")
            selections["codex"] = {
                "label": "ChatGPT / Codex",
                "older": old_model,
                "newer": new_model,
            }
            print("\nCodex models selected automatically:")
            print(f"  Older: {old_model}")
            print(f"  Newer: {new_model}")
        except Exception as exc:
            print(f"SKIPPED Codex automatic model comparison: {exc}")

    selection_file = None
    if selections:
        selection_file = _save_exp3_model_selection(selections)
        print(f"Recorded model selection: {selection_file}")
        config = selections["codex"]
        run_experiment_3(
            manifest, "codex", config["older"], config["newer"], 1, False
        )

        if _ensure_lizard_simple():
            output = RESULT_DIR / "experiment_3.csv"
            summary = RESULT_DIR / "experiment_3_summary.csv"
            _remove_if_exists(output)
            _remove_if_exists(summary)
            analyse_experiment(3, output, manifest)
            summarise_results(
                output, summary,
                ["dataset", "language", "provider", "model_group", "model"],
            )
            print("\nAutomatic Experiment 3 results written:")
            print(f"  Detailed: {output}")
            print(f"  Summary:  {summary}")

    print("\nExporting Claude Experiment 3 prompts for MANUAL website use...")
    export_claude_batch(
        3,
        manifest,
        CLAUDE_DIR / "experiment_3_claude",
        1,
        None,
        25,
    )
    print(
        "Use the same exported requests with a genuinely selectable older Claude "
        "model and newer Claude model, then import each response with its exact model label."
    )


def simple_claude_export():
    print("\n=== CLAUDE BATCH EXPORT ===")

    manifest = _build_auto_manifest()
    if not manifest:
        return

    if sys.stdin.isatty():
        raw = input("Experiment number [1]: ").strip() or "1"
    else:
        raw = "1"

    try:
        experiment = int(raw)
    except ValueError:
        print("Invalid experiment number.")
        return

    if experiment not in (1, 2, 3, 4):
        print("Experiment must be 1, 2, 3 or 4.")
        return

    output = CLAUDE_DIR / f"experiment_{experiment}_claude"
    export_claude_batch(
        experiment,
        manifest,
        output,
        1,
        ["Python", "Java", "C++", "C#"] if experiment == 2 else None,
    )

    print("\nPaste the generated .txt prompts into Claude.")
    print("Save Claude's response as:")
    print(f"  {CLAUDE_DIR / f'experiment_{experiment}_claude_response.txt'}")
    print("Then choose 'Import Claude response' from the menu.")


def simple_claude_import():
    print("\n=== CLAUDE BATCH IMPORT ===")

    if not sys.stdin.isatty():
        print("Run this from the interactive menu.")
        return

    raw = input("Experiment number [1]: ").strip() or "1"
    try:
        experiment = int(raw)
    except ValueError:
        print("Invalid experiment number.")
        return

    request_map = CLAUDE_DIR / f"experiment_{experiment}_claude.jsonl"
    response = CLAUDE_DIR / f"experiment_{experiment}_claude_response.txt"

    if not request_map.exists():
        print(f"Request map not found: {request_map}")
        return

    if not response.exists():
        entered = input(f"Claude response file [{response}]: ").strip()
        if entered:
            response = Path(entered)

    if not response.exists():
        print(f"Response file not found: {response}")
        return

    model_label = input("Exact Claude model used: ").strip() or "Claude"

    import_claude_batch(
        request_map,
        response,
        model_label,
        False,
    )

    print("Claude samples imported. Run 'results' or the relevant experiment analysis next.")


def simple_results():
    print("\n=== RESULTS ===")

    csv_files = sorted(RESULT_DIR.glob("*.csv"))
    if not csv_files:
        print("No result CSVs found yet.")
        return

    for path in csv_files:
        print(f"  {path}")


def simple_menu():
    ensure_dirs()

    actions = {
        "1": ("Quick trial — Human vs ChatGPT (100 records)", simple_quick),
        "2": ("Full Human vs ChatGPT dataset analysis", simple_full),
        "3": ("Analyse Back3474 automatically", simple_back3474),
        "4": ("Prepare CodeNet", simple_codenet),
        "5": ("Run Experiment 1 — Human vs AI", simple_exp1),
        "6": ("Run Experiment 2 — Languages", simple_exp2),
        "7": ("Run Experiment 3 — Older vs newer model", simple_exp3),
        "8": ("Run Experiment 4 — Prompt comparison", simple_exp4),
        "9": ("Export Claude prompt batch", simple_claude_export),
        "10": ("Import Claude response", simple_claude_import),
        "11": ("Show result files", simple_results),
        "12": ("Check AI tool installation", check_ai_tools),
    }

    while True:
        print("\n" + "=" * 58)
        print("THESIS B EXPERIMENT FRAMEWORK")
        print("=" * 58)

        for key, (label, _) in actions.items():
            print(f"{key:>2}. {label}")
        print(" 0. Exit")

        choice = input("\nChoose an option: ").strip()

        if choice == "0":
            return

        action = actions.get(choice)
        if not action:
            print("Invalid option.")
            continue

        try:
            action[1]()
        except KeyboardInterrupt:
            print("\nCancelled.")
        except Exception as exc:
            print(f"\nERROR: {exc}")

        if sys.stdin.isatty():
            input("\nPress Enter to return to the menu...")


def simple_command(command: str):
    ensure_dirs()

    commands = {
        "quick": simple_quick,
        "full": simple_full,
        "back3474": simple_back3474,
        "codenet": simple_codenet,
        "exp1": simple_exp1,
        "exp2": simple_exp2,
        "exp3": simple_exp3,
        "exp4": simple_exp4,
        "claude-export": simple_claude_export,
        "claude-import": simple_claude_import,
        "results": simple_results,
        "tools": check_ai_tools,
        "menu": simple_menu,
    }

    action = commands.get(command)
    if not action:
        raise ValueError(f"Unknown simple command: {command}")
    action()



def main():
    ensure_dirs()

    parser = argparse.ArgumentParser(
        description="Single-file Thesis B experiment framework"
    )
    sub = parser.add_subparsers(dest="command", required=True)

    # prepare-codenet
    p = sub.add_parser("prepare-codenet")
    p.add_argument("--root", required=True, type=Path)
    p.add_argument("--output", type=Path, default=MANIFEST_DIR / "codenet.jsonl")
    p.add_argument("--languages", nargs="+", default=DEFAULT_LANGUAGES)
    p.add_argument("--human-per-problem", type=int, default=DEFAULT_HUMAN_PER_PROBLEM)
    p.add_argument("--max-problems", type=int, default=0, help="0 = all eligible problems")
    p.add_argument("--seed", type=int, default=DEFAULT_SEED)

    # prepare Back3474 paired dataset
    p = sub.add_parser("prepare-back3474")
    p.add_argument("--input", required=True, type=Path)
    p.add_argument("--output", type=Path, default=MANIFEST_DIR / "back3474.jsonl")
    p.add_argument("--extract-dir", type=Path, default=WORK_DIR / "extracted")
    p.add_argument("--languages", nargs="+", default=["Python", "Java", "C++"])

    # stage already-existing paired samples without generating anything new
    p = sub.add_parser("stage-existing")
    p.add_argument("--experiment", required=True, type=int, choices=[1, 2])
    p.add_argument("--manifest", required=True, type=Path)
    p.add_argument("--languages", nargs="+", default=DEFAULT_LANGUAGES + ["Go"])
    p.add_argument("--datasets", nargs="+")

    # inspect a prepared manifest before running experiments
    p = sub.add_parser("inspect")
    p.add_argument("--manifest", required=True, type=Path)

    # normalise
    p = sub.add_parser("normalise-jsonl")
    p.add_argument("--input", required=True, type=Path)
    p.add_argument("--output", required=True, type=Path)
    p.add_argument("--dataset", required=True)

    # merge
    p = sub.add_parser("merge")
    p.add_argument("--inputs", nargs="+", required=True, type=Path)
    p.add_argument("--output", type=Path, default=MANIFEST_DIR / "all_datasets.jsonl")

    # fixed stratified study sample
    p = sub.add_parser("make-study-manifest")
    p.add_argument("--input", required=True, type=Path)
    p.add_argument("--output", type=Path, default=MANIFEST_DIR / "study_sample.jsonl")
    p.add_argument("--problems-per-dataset", type=int, default=25)
    p.add_argument("--seed", type=int, default=DEFAULT_SEED)
    p.add_argument("--languages", nargs="+", default=["Python", "Java", "C++"])

    # run exp 1/2/4
    p = sub.add_parser("run")
    p.add_argument("--experiment", required=True, type=int, choices=[1, 2, 4])
    p.add_argument("--manifest", required=True, type=Path)
    p.add_argument("--providers", nargs="+", choices=["codex"], default=["codex"])
    p.add_argument(
        "--provider-model",
        action="append",
        default=[],
        help="Example: --provider-model codex:MODEL_ID",
    )
    p.add_argument("--repeats", type=int, default=1)
    p.add_argument("--languages", nargs="+", default=DEFAULT_LANGUAGES)
    p.add_argument("--force", action="store_true")

    # run exp 3
    p = sub.add_parser("run-model-comparison")
    p.add_argument("--manifest", required=True, type=Path)
    p.add_argument("--provider", required=True, choices=["codex"])
    p.add_argument("--old-model", required=True)
    p.add_argument("--new-model", required=True)
    p.add_argument("--repeats", type=int, default=1)
    p.add_argument("--force", action="store_true")

    # Claude export
    p = sub.add_parser("claude-export")
    p.add_argument("--experiment", required=True, type=int, choices=[1, 2, 3, 4])
    p.add_argument("--manifest", required=True, type=Path)
    p.add_argument("--output", type=Path, default=CLAUDE_DIR / "claude_batch")
    p.add_argument("--repeats", type=int, default=1)
    p.add_argument("--languages", nargs="+")
    p.add_argument("--batch-size", type=int, default=25, help="Requests per Claude website batch; 0 = one file")

    # Claude import
    p = sub.add_parser("claude-import")
    p.add_argument("--request-map", required=True, type=Path)
    p.add_argument("--response", required=True, type=Path)
    p.add_argument("--model-label", required=True)
    p.add_argument("--force", action="store_true")

    # bulk Claude batch import
    p = sub.add_parser("claude-import-batches")
    p.add_argument("--batch-prefix", required=True, type=Path)
    p.add_argument("--model-label", required=True)
    p.add_argument("--force", action="store_true")

    # analyse the public Human-vs-AI Code Quality dataset directly
    p = sub.add_parser("analyse-hvai-quality")
    p.add_argument("--python", type=Path, help="Path to python_dataset.jsonl")
    p.add_argument("--java", type=Path, help="Path to java_dataset.jsonl")
    p.add_argument("--output", type=Path, default=RESULT_DIR / "hvai_quality_experiment1.csv")
    p.add_argument("--max-records", type=int, default=0, help="0 = all records; use a small value for a trial run")
    p.add_argument("--batch-size", type=int, default=250)
    p.add_argument(
        "--authors",
        nargs="+",
        default=["Human", "ChatGPT", "DeepSeek-Coder", "Qwen-Coder"],
    )

    # summarise any result CSV
    p = sub.add_parser("summarise")
    p.add_argument("--input", required=True, type=Path)
    p.add_argument("--output", required=True, type=Path)
    p.add_argument(
        "--group-by",
        nargs="+",
        default=["language", "source", "model"],
        help="Columns used to group results",
    )

    # analyse
    p = sub.add_parser("analyse")
    p.add_argument("--experiment", required=True, type=int, choices=[1, 2, 3, 4])
    p.add_argument("--output", type=Path)
    p.add_argument("--manifest", type=Path, help="Restrict analysis to rows in this study manifest")

    args = parser.parse_args()

    if args.command == "prepare-codenet":
        prepare_codenet(
            args.root,
            args.output,
            args.languages,
            args.human_per_problem,
            args.max_problems,
            args.seed,
        )
        return

    if args.command == "prepare-back3474":
        prepare_back3474(args.input, args.output, args.extract_dir, args.languages)
        return

    if args.command == "stage-existing":
        stage_existing_manifest(args.manifest, args.experiment, args.languages, args.datasets)
        return

    if args.command == "inspect":
        inspect_manifest(args.manifest)
        return

    if args.command == "normalise-jsonl":
        normalise_external_jsonl(
            args.input,
            args.output,
            args.dataset,
        )
        return

    if args.command == "merge":
        merge_manifests(args.inputs, args.output)
        return

    if args.command == "make-study-manifest":
        make_study_manifest(
            args.input, args.output, args.problems_per_dataset, args.seed, args.languages
        )
        return

    if args.command == "run":
        model_map = parse_provider_models(args.provider_model)

        # ensure providers have an entry
        for provider in args.providers:
            model_map.setdefault(provider.lower(), None)

        if args.experiment == 1:
            run_experiment_1(
                args.manifest,
                [p.lower() for p in args.providers],
                model_map,
                args.repeats,
                args.force,
            )

        elif args.experiment == 2:
            run_experiment_2(
                args.manifest,
                [p.lower() for p in args.providers],
                model_map,
                args.repeats,
                args.languages,
                args.force,
            )

        elif args.experiment == 4:
            run_experiment_4(
                args.manifest,
                [p.lower() for p in args.providers],
                model_map,
                args.repeats,
                args.force,
            )

        return

    if args.command == "run-model-comparison":
        run_experiment_3(
            args.manifest,
            args.provider,
            args.old_model,
            args.new_model,
            args.repeats,
            args.force,
        )
        return

    if args.command == "claude-export":
        export_claude_batch(
            args.experiment,
            args.manifest,
            args.output,
            args.repeats,
            args.languages,
            args.batch_size,
        )
        return

    if args.command == "claude-import":
        import_claude_batch(
            args.request_map,
            args.response,
            args.model_label,
            args.force,
        )
        return

    if args.command == "claude-import-batches":
        import_claude_batches(
            args.batch_prefix, args.model_label, args.force
        )
        return

    if args.command == "analyse-hvai-quality":
        analyse_hvai_quality(
            args.python,
            args.java,
            args.output,
            args.max_records,
            args.batch_size,
            args.authors,
        )
        return

    if args.command == "summarise":
        summarise_results(
            args.input,
            args.output,
            args.group_by,
        )
        return

    if args.command == "analyse":
        output = args.output or RESULT_DIR / f"experiment_{args.experiment}.csv"
        analyse_experiment(args.experiment, output, args.manifest)
        return


if __name__ == "__main__":
    # No arguments = easiest mode: interactive menu.
    if len(sys.argv) == 1:
        simple_menu()
    # Short commands for convenience.
    elif len(sys.argv) == 2 and sys.argv[1].lower() in SIMPLE_COMMANDS:
        simple_command(sys.argv[1].lower())
    # Keep the full V3 command-line interface available for advanced use.
    else:
        main()
