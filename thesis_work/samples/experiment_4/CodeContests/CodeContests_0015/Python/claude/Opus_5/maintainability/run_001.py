"""Length of a number after repeatedly replacing each digit d with d+1."""

import sys

MODULUS = 10**9 + 7
MAX_OPERATIONS = 200_001

# A digit only matters through how many operations remain once it first
# overflows past 9. `LENGTH_AFTER[t]` is the length that a freshly created
# digit 0 grows to after t further operations: it becomes "1" after 9 more
# steps and splits into "10" after 10 more.
_SPLIT_DELAY_ONE = 9
_SPLIT_DELAY_TWO = 10


def _build_length_table() -> list[int]:
    lengths = [0] * (MAX_OPERATIONS + 1)
    lengths[0] = 1
    for steps in range(1, MAX_OPERATIONS + 1):
        first = 1 if steps <= _SPLIT_DELAY_ONE - 1 else lengths[steps - _SPLIT_DELAY_ONE]
        second = 1 if steps <= _SPLIT_DELAY_TWO - 1 else lengths[steps - _SPLIT_DELAY_TWO]
        lengths[steps] = (first + second) % MODULUS
    return lengths


LENGTH_AFTER = _build_length_table()


def grown_length(digit: int, operations: int) -> int:
    """Length that a single ``digit`` reaches after ``operations`` steps."""
    steps_until_overflow = 9 - digit
    if operations < steps_until_overflow:
        return 1
    return LENGTH_AFTER[operations - steps_until_overflow]


def main() -> None:
    tokens = sys.stdin.buffer.read().split()
    test_count = int(tokens[0])

    answers = []
    cursor = 1
    for _ in range(test_count):
        digits = tokens[cursor].decode()
        operations = int(tokens[cursor + 1])
        cursor += 2

        total = sum(grown_length(int(character), operations) for character in digits)
        answers.append(total % MODULUS)

    sys.stdout.write("\n".join(map(str, answers)) + "\n")


if __name__ == "__main__":
    main()