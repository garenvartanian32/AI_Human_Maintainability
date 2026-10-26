"""Flip a single bit: report 1 for input 0, and 0 for input 1."""

import sys


def main() -> None:
    bit = int(sys.stdin.read().strip())
    print(1 - bit)


if __name__ == "__main__":
    main()