"""Count completed step-ups and step-downs from a sequence of foot movements."""

import sys

RAISE_SUFFIX = "u"
END_OF_INPUT = 0

# Where the pair of feet currently is.
ON_FLOOR = 0
RISING = 1
ON_STEP = 2
FALLING = 3


def count_completed_moves(movements: list[str]) -> int:
    """Return how many times both feet finished arriving on or off the step."""
    position = ON_FLOOR
    completed = 0

    for movement in movements:
        is_raise = movement[1] == RAISE_SUFFIX

        if position == ON_FLOOR:
            position = RISING
        elif position == RISING:
            if is_raise:
                position = ON_STEP
                completed += 1
            else:
                position = ON_FLOOR
        elif position == ON_STEP:
            position = FALLING
        else:  # FALLING
            if is_raise:
                position = ON_STEP
            else:
                position = ON_FLOOR
                completed += 1

    return completed


def main() -> None:
    tokens = sys.stdin.buffer.read().split()
    cursor = 0
    answers = []

    while cursor < len(tokens):
        movement_count = int(tokens[cursor])
        cursor += 1
        if movement_count == END_OF_INPUT:
            break

        movements = [tokens[cursor + i].decode() for i in range(movement_count)]
        cursor += movement_count
        answers.append(count_completed_moves(movements))

    sys.stdout.write("\n".join(map(str, answers)) + "\n")


if __name__ == "__main__":
    main()