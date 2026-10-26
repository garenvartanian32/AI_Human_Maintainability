"""Enumerate every length-n string in normal form, in lexicographic order."""

import sys

FIRST_LETTER = "a"


def normal_form_strings(length: int) -> list[str]:
    """Return all normal-form strings of the given length.

    A string is in normal form when each letter is at most one past the
    largest letter used before it. That keeps exactly one representative of
    every partition of the positions, and building left to right in
    alphabetical order yields them already sorted.
    """
    results: list[str] = []
    buffer = [FIRST_LETTER] * length

    def extend(position: int, highest_used: int) -> None:
        if position == length:
            results.append("".join(buffer))
            return

        for letter_index in range(highest_used + 1):
            buffer[position] = chr(ord(FIRST_LETTER) + letter_index)
            next_highest = highest_used + 1 if letter_index == highest_used else highest_used
            extend(position + 1, next_highest)

    extend(0, 0)
    return results


def main() -> None:
    length = int(sys.stdin.buffer.read().split()[0])
    sys.stdout.write("\n".join(normal_form_strings(length)) + "\n")


if __name__ == "__main__":
    main()