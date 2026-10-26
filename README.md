# AI-Generated vs Human-Written Code: Maintainability Analysis

**Thesis experimental framework | 2026**

This repository contains the framework and supporting material for a comparative investigation of the structural maintainability of AI-generated and human-written code. The central research question is:

> How does the maintainability of AI-generated code compare to human-written code?

The analysis covers **Python, Java and C++**, using the same static-analysis approach to evaluate both code sources.

## Experiments

| Experiment | Comparison |
| --- | --- |
| 1. Code source | Human-written code versus Claude and Codex |
| 2. Programming language | Python versus Java versus C++ |
| 3. Model generation | Older versus newer models within each AI provider |
| 4. Prompting strategy | Standard versus maintainability-focused prompts |

## Maintainability metrics

The framework uses **Lizard** and derived measurements to evaluate four complementary indicators:

| Metric | Interpretation |
| --- | --- |
| Maintainability Index (MI) | Higher generally indicates better estimated maintainability |
| Cyclomatic Complexity (CC) | Lower generally indicates simpler control flow |
| Lines of Code (LOC) | Lower indicates a smaller implementation, not necessarily better code |
| Halstead Volume (HV) | Lower generally indicates lower operator/operand-based volume |

These are **indirect, structural indicators**. They do not by themselves establish functional correctness or the time developers would need to maintain the code.

## Datasets

Programming tasks and existing solutions are drawn from [IBM Project CodeNet](https://github.com/IBM/Project_CodeNet), [Google DeepMind CodeContests](https://github.com/google-deepmind/code_contests), [MultiPL-E](https://github.com/nuprl/MultiPL-E), and the [Back3474 AI/Human-generated code dataset](https://github.com/Back3474/AI-Human-Generated-Program-Code-Dataset). Original datasets remain attributed to their respective maintainers.

## Getting started

**Requirements:** Python, [Lizard](https://github.com/terryyin/lizard), and the downloaded datasets. Automated Codex generation requires an authenticated Codex CLI; **Claude uses a manual prompt export/response import workflow**, without Claude API keys.

```bash
python -m pip install --upgrade lizard
python AI_Human_Maintainability.py
```

The second command opens the framework's interactive menu. Check CLI availability with:

```bash
python AI_Human_Maintainability.py tools
```

After preparing dataset manifests, the framework can create a reproducible sample using **seed 4050**, selecting up to **25 eligible problems per dataset**:

```bash
python AI_Human_Maintainability.py make-study-manifest --input thesis_work/manifests/all_datasets.jsonl --output thesis_work/manifests/study_sample.jsonl --problems-per-dataset 25 --seed 4050 --languages Python Java "C++"
```

For example, to analyse collected samples from Experiment 1:

```bash
python AI_Human_Maintainability.py analyse --experiment 1 --manifest thesis_work/manifests/study_sample.jsonl
```

Run commands from the directory containing `AI_Human_Maintainability.py`. Dataset preparation and AI sample generation must be completed before metric analysis; see the interactive menu and framework help for additional commands.

## Repository contents

- `AI_Human_Maintainability.py` — experiment orchestration and metric extraction
- `datasets/` — locally prepared third-party dataset inputs (when included)
- `thesis_work/manifests/` — normalised and sampled problem lists
- `thesis_work/samples/` — collected human-written and AI-generated solutions
- `thesis_work/results/` — per-experiment CSV measurements and summaries
- `thesis_work/claude_batches/` — manual Claude prompt and response batches
- `figures/` — visualisations used to present the results

Some folders are generated when the framework runs and may not be present in a fresh clone. Large source datasets may need to be downloaded separately.

## Interpretation and limitations

The experiments compare measured **structural maintainability**, not every dimension of software quality. Results can depend on the dataset, language, model, prompt and task. A favourable metric score should not be interpreted as proof of superior correctness, security or real-world maintenance effort.

**Purpose:** Academic research and reproducible comparison of human-written and AI-generated code.