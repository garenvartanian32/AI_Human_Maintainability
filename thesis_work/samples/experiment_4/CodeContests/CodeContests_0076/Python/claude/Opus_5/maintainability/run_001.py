"""Decide whether apples of weight 100 or 200 can be split into equal halves."""

import sys

WEIGHT_UNIT = 100


def can_split_evenly(unit_weights: list[int]) -> bool:
    """True when some subset of the weights sums to exactly half the total.

    Weights are multiples of ``WEIGHT_UNIT``, so dividing through keeps the
    subset-sum table small. A bitmask tracks every reachable subtotal at once.
    """
    total = sum(unit_weights)
    if total % 2 != 0:
        return False

    reachable_subtotals = 1  # bit i set means subtotal i is reachable
    for weight in unit_weights:
        reachable_subtotals |= reachable_subtotals << weight

    return bool((reachable_subtotals >> (total // 2)) & 1)


def main() -> None:
    tokens = sys.stdin.buffer.read().split()
    apple_count = int(tokens[0])
    unit_weights = [int(tokens[1 + i]) // WEIGHT_UNIT for i in range(apple_count)]

    print("YES" if can_split_evenly(unit_weights) else "NO")


if __name__ == "__main__":
    main()