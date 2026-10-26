"""Report the sign of the product of every integer in [a, b]."""

import sys


def main() -> None:
    low, high = map(int, sys.stdin.read().split())

    if low <= 0 <= high:
        # Zero is in range, so it annihilates the product.
        print("Zero")
    elif low > 0:
        # Every factor is positive.
        print("Positive")
    else:
        # Every factor is negative; the sign follows the count's parity.
        factor_count = high - low + 1
        print("Positive" if factor_count % 2 == 0 else "Negative")


if __name__ == "__main__":
    main()