"""Report whether the product of two integers is odd or even."""

import sys


def main() -> None:
    first, second = map(int, sys.stdin.buffer.read().split())

    # A product is odd only when neither factor contributes a factor of two.
    product_is_odd = first % 2 == 1 and second % 2 == 1
    print("Odd" if product_is_odd else "Even")


if __name__ == "__main__":
    main()