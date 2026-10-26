#!/usr/bin/env python3
import argparse
import json
from pathlib import Path
from datasets import load_dataset

LANGUAGE_CODES = {"Python": 3, "C++": 2, "Java": 4}
EXTENSIONS = {"Python": ".py", "C++": ".cpp", "Java": ".java"}


def unpack_solutions(value):
    if not value:
        return []
    if isinstance(value, dict):
        return list(zip(value.get("language") or [], value.get("solution") or []))
    if isinstance(value, list):
        return [
            (item.get("language"), item.get("solution"))
            for item in value
            if isinstance(item, dict)
        ]
    return []


def json_safe(value):
    if value is None or isinstance(value, (str, int, float, bool)):
        return value
    if isinstance(value, dict):
        return {str(k): json_safe(v) for k, v in value.items()}
    if isinstance(value, (list, tuple)):
        return [json_safe(v) for v in value]
    try:
        return value.item()
    except Exception:
        return str(value)


def main():
    parser = argparse.ArgumentParser()
    parser.add_argument("--problems", type=int, default=100)
    parser.add_argument("--human-per-language", type=int, default=3)
    parser.add_argument("--split", default="train")
    parser.add_argument(
        "--extract-dir",
        type=Path,
        default=Path("thesis_work/extracted/codecontests"),
    )
    parser.add_argument(
        "--output",
        type=Path,
        default=Path("codecontests_export.jsonl"),
    )
    args = parser.parse_args()

    args.extract_dir.mkdir(parents=True, exist_ok=True)
    args.output.parent.mkdir(parents=True, exist_ok=True)

    print("Loading official DeepMind CodeContests from Hugging Face...")
    print(f"Split: {args.split}")
    print(
        f"Selecting {args.problems} problems with at least "
        f"{args.human_per_language} correct human solutions in Python 3, Java and C++."
    )

    dataset = load_dataset("deepmind/code_contests", split=args.split, streaming=True)

    selected = 0
    rows_written = 0

    with args.output.open("w", encoding="utf-8") as out:
        for dataset_index, row in enumerate(dataset):
            buckets = {language: [] for language in LANGUAGE_CODES}

            for language_code, source_code in unpack_solutions(row.get("solutions")):
                if not isinstance(source_code, str) or not source_code.strip():
                    continue
                for language, wanted_code in LANGUAGE_CODES.items():
                    if language_code == wanted_code:
                        if len(buckets[language]) < args.human_per_language:
                            buckets[language].append(source_code)
                        break

            if not all(
                len(buckets[language]) >= args.human_per_language
                for language in LANGUAGE_CODES
            ):
                continue

            selected += 1
            problem_id = f"CodeContests_{selected:04d}"
            problem_dir = args.extract_dir / problem_id

            for language in ("Python", "Java", "C++"):
                language_dir = problem_dir / language.replace("+", "p")
                language_dir.mkdir(parents=True, exist_ok=True)

                human_files = []
                for solution_no, source_code in enumerate(
                    buckets[language][: args.human_per_language], start=1
                ):
                    source_path = language_dir / f"human_{solution_no:03d}{EXTENSIONS[language]}"
                    source_path.write_text(source_code, encoding="utf-8")
                    human_files.append(str(source_path))

                record = {
                    "problem_id": problem_id,
                    "language": language,
                    "problem": row.get("description") or row.get("name") or problem_id,
                    "human_files": human_files,
                    "test_command": None,
                    "metadata": {
                        "original_name": row.get("name", ""),
                        "split": args.split,
                        "dataset_index": dataset_index,
                        "source": json_safe(row.get("source")),
                        "difficulty": json_safe(row.get("difficulty")),
                        "cf_contest_id": json_safe(row.get("cf_contest_id")),
                        "cf_index": json_safe(row.get("cf_index")),
                        "cf_rating": json_safe(row.get("cf_rating")),
                        "public_tests": json_safe(row.get("public_tests")),
                    },
                }
                out.write(json.dumps(record, ensure_ascii=False) + "\n")
                rows_written += 1

            print(f"Selected {selected}/{args.problems}: {row.get('name', problem_id)}")
            if selected >= args.problems:
                break

    if selected < args.problems:
        raise RuntimeError(
            f"Only found {selected} eligible problems in split '{args.split}'. Requested {args.problems}."
        )

    print("\nDONE")
    print(f"Problems selected: {selected}")
    print(f"Manifest rows:     {rows_written}")
    print(f"Human code files:  {selected * 3 * args.human_per_language}")
    print(f"Export:            {args.output}")
    print(f"Extracted code:    {args.extract_dir}")


if __name__ == "__main__":
    main()
