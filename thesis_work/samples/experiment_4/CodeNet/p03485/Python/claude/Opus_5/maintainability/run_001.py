"""Average of two integers, rounded up when it lands on a half."""

import sys


def main() -> None:
    first, second = map(int, sys.stdin.read().split())

    # Adding one before the integer division rounds .5 upwards.
    print((first + second + 1) // 2)


if __name__ == "__main__":
    main()