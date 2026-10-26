"""For each query, find the smallest positive multiple of d outside [l, r]."""

import sys


def smallest_multiple_outside(l: int, r: int, d: int) -> int:
    """Return the least positive multiple of ``d`` that is not within [l, r].

    If ``d`` itself already falls outside the forbidden range it is the answer.
    Otherwise every multiple up to ``r`` is blocked, so the first free one is
    the next multiple after ``r``.
    """
    if d < l or d > r:
        return d
    return (r // d + 1) * d


def main() -> None:
    tokens = sys.stdin.buffer.read().split()
    query_count = int(tokens[0])

    answers = []
    cursor = 1
    for _ in range(query_count):
        l, r, d = (int(tokens[cursor + offset]) for offset in range(3))
        cursor += 3
        answers.append(smallest_multiple_outside(l, r, d))

    sys.stdout.write("\n".join(map(str, answers)) + "\n")


if __name__ == "__main__":
    main()