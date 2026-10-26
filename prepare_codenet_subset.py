#!/usr/bin/env python3

from __future__ import annotations

import argparse
import csv
import hashlib
import io
import json
import random
import re
import shutil
import sys
import tarfile
from pathlib import Path
from typing import Dict, List, Tuple

DEFAULT_LANGUAGES = ["Python", "Java", "C++"]


def stable_rng(seed: int, label: str) -> random.Random:
    digest = hashlib.sha256(f"{seed}:{label}".encode("utf-8")).digest()
    return random.Random(int.from_bytes(digest[:8], "big"))


def parse_metadata_csv(data: bytes, languages: List[str]) -> Dict[str, List[Tuple[str, str]]]:
    text = data.decode("utf-8-sig", errors="ignore")
    reader = csv.DictReader(io.StringIO(text))
    by_language: Dict[str, List[Tuple[str, str]]] = {lang: [] for lang in languages}

    for row in reader:
        if (row.get("status") or "").strip() != "Accepted":
            continue

        language = (row.get("language") or "").strip()
        if language not in by_language:
            continue

        submission_id = (row.get("submission_id") or "").strip()
        ext = (row.get("filename_ext") or "").strip().lstrip(".")
        if not submission_id or not ext:
            continue

        by_language[language].append((submission_id, ext))

    return by_language


def write_member(tf: tarfile.TarFile, member: tarfile.TarInfo, destination: Path) -> None:
    source = tf.extractfile(member)
    if source is None:
        return
    destination.parent.mkdir(parents=True, exist_ok=True)
    with source, destination.open("wb") as out:
        shutil.copyfileobj(source, out, length=1024 * 1024)


def first_pass(
    archive: Path,
    languages: List[str],
    human_per_problem: int,
) -> Dict[str, Dict[str, List[Tuple[str, str]]]]:
    eligible: Dict[str, Dict[str, List[Tuple[str, str]]]] = {}
    metadata_re = re.compile(r"(?:^|/)metadata/(p\d+)\.csv$")

    print("PASS 1/2: scanning CodeNet metadata inside the archive...")
    member_count = 0
    metadata_count = 0

    with tarfile.open(archive, mode="r|gz") as tf:
        for member in tf:
            member_count += 1
            if member_count % 100000 == 0:
                print(
                    f"  scanned {member_count:,} archive entries; "
                    f"eligible problems so far: {len(eligible):,}",
                    flush=True,
                )

            if not member.isfile():
                continue

            name = member.name.replace("\\", "/")
            match = metadata_re.search(name)
            if not match:
                continue

            metadata_count += 1
            source = tf.extractfile(member)
            if source is None:
                continue
            with source:
                data = source.read()

            by_language = parse_metadata_csv(data, languages)
            if all(len(by_language[lang]) >= human_per_problem for lang in languages):
                eligible[match.group(1)] = by_language

    print(
        f"PASS 1 complete: inspected {metadata_count:,} problem metadata files; "
        f"{len(eligible):,} problems have >= {human_per_problem} accepted "
        f"solutions in every requested language."
    )
    return eligible


def choose_subset(
    eligible: Dict[str, Dict[str, List[Tuple[str, str]]]],
    problem_count: int,
    languages: List[str],
    human_per_problem: int,
    seed: int,
):
    ids = sorted(eligible)
    if not ids:
        raise RuntimeError("No eligible CodeNet problems were found for the requested languages.")

    if problem_count <= 0:
        selected_ids = ids
    elif problem_count > len(ids):
        print(
            f"Requested {problem_count} problems but only {len(ids)} are eligible; "
            "using all eligible problems."
        )
        selected_ids = ids
    else:
        selected_ids = sorted(random.Random(seed).sample(ids, problem_count))

    selections: Dict[str, Dict[str, List[Tuple[str, str]]]] = {}
    for pid in selected_ids:
        selections[pid] = {}
        for language in languages:
            candidates = eligible[pid][language][:]
            rng = stable_rng(seed, f"{pid}:{language}")
            rng.shuffle(candidates)
            selections[pid][language] = candidates[:human_per_problem]

    return selections


def second_pass(
    archive: Path,
    output: Path,
    selections: Dict[str, Dict[str, List[Tuple[str, str]]]],
    languages: List[str],
) -> None:
    selected_ids = set(selections)

    wanted_sources = set()
    for pid, by_language in selections.items():
        for language, items in by_language.items():
            for submission_id, ext in items:
                wanted_sources.add(f"data/{pid}/{language}/{submission_id}.{ext}")

    found_sources = set()
    found_metadata = set()
    found_descriptions = set()
    found_problem_list = False

    metadata_re = re.compile(r"(?:^|/)metadata/(p\d+)\.csv$")
    problem_list_re = re.compile(r"(?:^|/)metadata/problem_list\.csv$")
    desc_re = re.compile(r"(?:^|/)(problem_descriptions/(p\d+)\.html|doc/problem_descriptions/(p\d+)\.html)$")
    source_re = re.compile(r"(?:^|/)(data/(p\d+)/(Python|Java|C\+\+)/([^/]+))$")

    print("PASS 2/2: extracting only the selected CodeNet files...")
    member_count = 0

    with tarfile.open(archive, mode="r|gz") as tf:
        for member in tf:
            member_count += 1
            if member_count % 100000 == 0:
                print(
                    f"  scanned {member_count:,} archive entries; "
                    f"source files extracted: {len(found_sources):,}/{len(wanted_sources):,}",
                    flush=True,
                )

            if not member.isfile():
                continue

            name = member.name.replace("\\", "/")

            if not found_problem_list and problem_list_re.search(name):
                write_member(tf, member, output / "metadata" / "problem_list.csv")
                found_problem_list = True
                continue

            mm = metadata_re.search(name)
            if mm and mm.group(1) in selected_ids:
                pid = mm.group(1)
                write_member(tf, member, output / "metadata" / f"{pid}.csv")
                found_metadata.add(pid)
                continue

            dm = desc_re.search(name)
            if dm:
                pid = dm.group(2) or dm.group(3)
                if pid in selected_ids:
                    rel = dm.group(1)
                    write_member(tf, member, output / Path(rel))
                    found_descriptions.add(pid)
                continue

            sm = source_re.search(name)
            if sm:
                rel = sm.group(1)
                pid = sm.group(2)
                language = sm.group(3)
                if pid in selected_ids and language in languages and rel in wanted_sources:
                    write_member(tf, member, output / Path(rel))
                    found_sources.add(rel)

    missing_sources = sorted(wanted_sources - found_sources)
    missing_metadata = sorted(selected_ids - found_metadata)

    print("PASS 2 complete.")
    print(f"  selected problems:     {len(selected_ids):,}")
    print(f"  metadata files:        {len(found_metadata):,}/{len(selected_ids):,}")
    print(f"  problem descriptions: {len(found_descriptions):,}/{len(selected_ids):,}")
    print(f"  source files:          {len(found_sources):,}/{len(wanted_sources):,}")

    if not found_problem_list:
        print("WARNING: metadata/problem_list.csv was not found in the archive.")
    if missing_metadata:
        print(f"WARNING: {len(missing_metadata)} selected metadata files were not found.")
    if missing_sources:
        print(f"ERROR: {len(missing_sources)} selected source files were not found.")
        for path in missing_sources[:20]:
            print(f"  missing: {path}")
        if len(missing_sources) > 20:
            print(f"  ... and {len(missing_sources) - 20} more")
        raise RuntimeError("Subset extraction was incomplete.")


def main() -> None:
    parser = argparse.ArgumentParser(
        description="Extract a reproducible, multi-language Project CodeNet subset from Project_CodeNet.tar.gz"
    )
    parser.add_argument("--archive", required=True, type=Path)
    parser.add_argument("--output", required=True, type=Path)
    parser.add_argument("--problems", type=int, default=100)
    parser.add_argument("--languages", nargs="+", default=DEFAULT_LANGUAGES)
    parser.add_argument("--human-per-problem", type=int, default=3)
    parser.add_argument("--seed", type=int, default=4050)
    parser.add_argument("--force", action="store_true")
    args = parser.parse_args()

    archive = args.archive.resolve()
    output = args.output.resolve()

    if not archive.exists():
        raise FileNotFoundError(archive)

    if output.exists():
        if not args.force:
            raise FileExistsError(
                f"Output already exists: {output}\n"
                "Delete it first or rerun with --force."
            )
        shutil.rmtree(output)

    output.mkdir(parents=True, exist_ok=True)

    languages = args.languages
    eligible = first_pass(archive, languages, args.human_per_problem)
    selections = choose_subset(
        eligible,
        args.problems,
        languages,
        args.human_per_problem,
        args.seed,
    )

    report = {
        "archive": str(archive),
        "problem_count": len(selections),
        "languages": languages,
        "human_per_problem": args.human_per_problem,
        "seed": args.seed,
        "selected_problems": {
            pid: {
                language: [
                    {"submission_id": sid, "extension": ext}
                    for sid, ext in items
                ]
                for language, items in by_language.items()
            }
            for pid, by_language in selections.items()
        },
    }
    (output / "subset_selection.json").write_text(
        json.dumps(report, indent=2, ensure_ascii=False),
        encoding="utf-8",
    )

    second_pass(archive, output, selections, languages)

    print("\nREADY")
    print(f"Subset root: {output}")
    print("Use this as --root for New_Thesis_B_Script.py prepare-codenet.")


if __name__ == "__main__":
    try:
        main()
    except KeyboardInterrupt:
        print("\nCancelled.", file=sys.stderr)
        raise SystemExit(130)
