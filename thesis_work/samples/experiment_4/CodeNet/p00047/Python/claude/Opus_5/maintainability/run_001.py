"""Track which cup hides the ball through a sequence of swaps."""

import sys

STARTING_CUP = "A"


def main() -> None:
    ball_location = STARTING_CUP

    for line in sys.stdin:
        line = line.strip()
        if not line:
            continue

        left_cup, right_cup = line.split(",")

        # The ball only moves when its own cup is one of the two swapped.
        if ball_location == left_cup:
            ball_location = right_cup
        elif ball_location == right_cup:
            ball_location = left_cup

    print(ball_location)


if __name__ == "__main__":
    main()