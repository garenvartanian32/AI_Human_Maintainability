"""List every length-n string in normal form, in lexicographic order."""

import sys

FIRST_LETTER = "a"


def normal_form_strings(length: int) -> list[str]:
    """All strings where each letter is at most one past the largest so far.

    That rule keeps exactly one representative of every way of partitioning the
    positions, and generating letters in alphabetical order emits the strings
    already sorted.
    """
    results: list[str] = []
    buffer = [FIRST_LETTER] * length

    def extend(position: int, letters_used: int) -> None:
        if position == length:
            results.append("".join(buffer))
            return

        for letter_index in range(letters_used + 1):
            buffer[position] = chr(ord(FIRST_LETTER) + letter_index)
            next_used = letters_used + 1 if letter_index == letters_used else letters_used
            extend(position + 1, next_used)

    extend(0, 0)
    return results


def main() -> None:
    length = int(sys.stdin.read().strip())
    sys.stdout.write("\n".join(normal_form_strings(length)) + "\n")


if __name__ == "__main__":
    main()