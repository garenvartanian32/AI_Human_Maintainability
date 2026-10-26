"""Longest road distance reachable within a straight-line limit."""

import math
import sys

DECIMAL_PLACES = 12
MINIMUM_LEG = 1.0


def longest_reachable(limit: float) -> float:
    """Return the greatest total road length within straight-line ``limit``.

    A route is a horizontal leg plus a vertical leg. For an integral number of
    horizontal blocks the vertical leg can stretch to whatever the straight-line
    budget still allows, but never below one block. The unconstrained diagonal
    is also a candidate, so it seeds the search.
    """
    best = limit * math.sqrt(2.0)

    for horizontal in range(1, int(math.floor(limit + 1e-9)) + 1):
        remaining_squared = max(limit * limit - float(horizontal) * horizontal, 0.0)
        vertical = max(MINIMUM_LEG, math.sqrt(remaining_squared))
        best = max(best, horizontal + vertical)

    return best


def main() -> None:
    limit = float(sys.stdin.read().strip())
    print(f"{longest_reachable(limit):.{DECIMAL_PLACES}f}")


if __name__ == "__main__":
    main()