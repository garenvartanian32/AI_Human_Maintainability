"""Count the interior squares of a grid — those not touching any edge."""

import sys


def main() -> None:
    rows, columns = map(int, sys.stdin.read().split())

    # Stripping the outermost row and column from each side leaves the squares
    # that have neighbours on all four sides.
    print((rows - 1) * (columns - 1))


if __name__ == "__main__":
    main()