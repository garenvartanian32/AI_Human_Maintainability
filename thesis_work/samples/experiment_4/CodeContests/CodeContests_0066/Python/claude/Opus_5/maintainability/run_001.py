"""Interleave two partial edit logs into one consistent history."""

import sys

PLACEHOLDER = 0
IMPOSSIBLE = "-1"


def merge_logs(initial_size: int, first: list[int], second: list[int]) -> list[int] | None:
    """Return an interleaving of the two logs, or None when none exists.

    An entry may be replayed only if it targets a position that already exists.
    A ``PLACEHOLDER`` entry appends a new position, growing the document. Taking
    whichever log has a currently-valid head is safe: deferring a valid entry
    can never unlock anything the other log needs.
    """
    merged: list[int] = []
    size = initial_size
    first_index = second_index = 0

    while first_index < len(first) or second_index < len(second):
        if first_index < len(first) and _can_replay(first[first_index], size):
            entry = first[first_index]
            first_index += 1
        elif second_index < len(second) and _can_replay(second[second_index], size):
            entry = second[second_index]
            second_index += 1
        else:
            return None

        if entry == PLACEHOLDER:
            size += 1
        merged.append(entry)

    return merged


def _can_replay(entry: int, size: int) -> bool:
    return entry == PLACEHOLDER or entry <= size


def main() -> None:
    tokens = sys.stdin.buffer.read().split()
    cursor = 0
    test_count = int(tokens[cursor])
    cursor += 1

    answers = []
    for _ in range(test_count):
        initial_size = int(tokens[cursor])
        first_length = int(tokens[cursor + 1])
        second_length = int(tokens[cursor + 2])
        cursor += 3

        first = [int(token) for token in tokens[cursor : cursor + first_length]]
        cursor += first_length
        second = [int(token) for token in tokens[cursor : cursor + second_length]]
        cursor += second_length

        merged = merge_logs(initial_size, first, second)
        answers.append(" ".join(map(str, merged)) if merged is not None else IMPOSSIBLE)

    sys.stdout.write("\n".join(answers) + "\n")


if __name__ == "__main__":
    main()