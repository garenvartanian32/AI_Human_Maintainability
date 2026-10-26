"""Shortest walk on a city grid, allowing corner cuts around fountains."""

import sys
from bisect import bisect_left
from math import pi, sqrt

BLOCK_LENGTH = 100.0
FOUNTAIN_RADIUS = 10.0

# Cutting a corner around a fountain replaces two quarter-block legs with a
# quarter arc, saving this much; a fountain sitting directly on a straight
# stretch instead forces a detour of this much.
CORNER_SAVING = FOUNTAIN_RADIUS * (2 - pi / 2)
STRAIGHT_DETOUR = FOUNTAIN_RADIUS * (pi - 2)


def longest_increasing_run(points: list[tuple[int, int]]) -> int:
    """Length of the longest strictly increasing chain by the second coordinate.

    Corner cuts can only be chained along a monotone staircase, so the number of
    usable fountains is exactly this chain length.
    """
    tails: list[int] = []
    for _, second in points:
        slot = bisect_left(tails, second)
        if slot == len(tails):
            tails.append(second)
        else:
            tails[slot] = second
    return len(tails)


def main() -> None:
    tokens = sys.stdin.buffer.read().split()
    start_x, start_y, end_x, end_y = (int(tokens[index]) for index in range(4))
    fountain_count = int(tokens[4])

    fountains = []
    cursor = 5
    for _ in range(fountain_count):
        fountains.append((int(tokens[cursor]), int(tokens[cursor + 1])))
        cursor += 2

    walk = (abs(start_x - end_x) + abs(start_y - end_y)) * BLOCK_LENGTH

    # A purely horizontal or vertical trip has no corners to cut; a fountain in
    # the way only costs extra.
    if start_x == end_x or start_y == end_y:
        blocked = _straight_path_blocked(start_x, start_y, end_x, end_y, fountains)
        print(f"{walk + (STRAIGHT_DETOUR if blocked else 0.0):.15f}")
        return

    # Reflect coordinates so the trip always runs in the increasing direction,
    # which turns "usable fountains" into a plain increasing subsequence.
    x_sign = 1 if end_x > start_x else -1
    y_sign = 1 if end_y > start_y else -1
    low_x, high_x = sorted((start_x, end_x))
    low_y, high_y = sorted((start_y, end_y))

    inside = sorted(
        (x * x_sign, y * y_sign)
        for x, y in fountains
        if low_x < x < high_x and low_y < y < high_y
    )

    print(f"{walk - longest_increasing_run(inside) * CORNER_SAVING:.15f}")


def _straight_path_blocked(
    start_x: int, start_y: int, end_x: int, end_y: int, fountains: list[tuple[int, int]]
) -> bool:
    if start_y == end_y:
        low, high = sorted((start_x, end_x))
        return any(y == start_y and low < x < high for x, y in fountains)

    low, high = sorted((start_y, end_y))
    return any(x == start_x and low < y < high for x, y in fountains)


if __name__ == "__main__":
    main()