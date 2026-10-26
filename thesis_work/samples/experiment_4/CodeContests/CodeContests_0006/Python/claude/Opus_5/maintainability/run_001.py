"""Sum of minimum pairwise distances between points that eventually meet."""

import sys


class FenwickTree:
    """Binary indexed tree tracking both a count and a sum of coordinates."""

    def __init__(self, size: int) -> None:
        self._size = size
        self._counts = [0] * (size + 1)
        self._coordinate_sums = [0] * (size + 1)

    def add(self, position: int, coordinate: int) -> None:
        """Record one point with the given coordinate at ``position``."""
        while position <= self._size:
            self._counts[position] += 1
            self._coordinate_sums[position] += coordinate
            position += position & -position

    def prefix(self, position: int) -> tuple[int, int]:
        """Return (count, coordinate sum) over ranks 1..``position``."""
        count = 0
        coordinate_sum = 0
        while position > 0:
            count += self._counts[position]
            coordinate_sum += self._coordinate_sums[position]
            position -= position & -position
        return count, coordinate_sum


def main() -> None:
    tokens = sys.stdin.buffer.read().split()
    point_count = int(tokens[0])
    coordinates = [int(value) for value in tokens[1 : 1 + point_count]]
    velocities = [int(value) for value in tokens[1 + point_count : 1 + 2 * point_count]]

    # Two points converge exactly when the left one is not slower than the
    # right one, so we sweep by coordinate and query smaller-or-equal speeds.
    velocity_rank = {speed: rank for rank, speed in enumerate(sorted(set(velocities)), start=1)}
    tree = FenwickTree(len(velocity_rank))

    total_distance = 0
    for coordinate, velocity in sorted(zip(coordinates, velocities)):
        earlier_count, earlier_sum = tree.prefix(velocity_rank[velocity])
        total_distance += earlier_count * coordinate - earlier_sum
        tree.add(velocity_rank[velocity], coordinate)

    print(total_distance)


if __name__ == "__main__":
    main()