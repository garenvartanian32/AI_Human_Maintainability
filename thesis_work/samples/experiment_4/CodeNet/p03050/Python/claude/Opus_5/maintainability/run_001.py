"""Sum of every m where floor(n/m) equals n mod m."""

import sys


def main() -> None:
    n = int(sys.stdin.read().strip())

    # Writing q for the shared value, n = q*m + q = q*(m + 1), so m + 1 must
    # divide n. Enumerating divisors of n therefore enumerates all candidates.
    total = 0
    divisor = 1
    while divisor * divisor <= n:
        if n % divisor == 0:
            for candidate in {divisor - 1, n // divisor - 1}:
                if candidate >= 1 and n // candidate == n % candidate:
                    total += candidate
        divisor += 1

    print(total)


if __name__ == "__main__":
    main()