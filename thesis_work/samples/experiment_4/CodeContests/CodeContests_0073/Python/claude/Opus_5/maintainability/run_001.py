"""Rewrite a sum of 1s, 2s and 3s so its summands appear in ascending order."""

import sys

PLUS = "+"


def main() -> None:
    expression = sys.stdin.read().strip()

    # Only the multiset of summands matters, so sorting them is enough.
    summands = sorted(expression.split(PLUS))
    print(PLUS.join(summands))


if __name__ == "__main__":
    main()