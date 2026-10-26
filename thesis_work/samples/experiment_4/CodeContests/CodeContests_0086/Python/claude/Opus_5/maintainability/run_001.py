"""Largest permutation reachable using an allowed set of position swaps."""

import sys
from collections import defaultdict


class DisjointSet:
    """Union-find over positions, with path compression."""

    def __init__(self, size: int) -> None:
        self._parent = list(range(size + 1))

    def find(self, position: int) -> int:
        root = position
        while self._parent[root] != root:
            root = self._parent[root]
        while self._parent[position] != root:
            self._parent[position], position = root, self._parent[position]
        return root

    def union(self, first: int, second: int) -> None:
        first_root, second_root = self.find(first), self.find(second)
        if first_root != second_root:
            self._parent[first_root] = second_root


def main() -> None:
    tokens = sys.stdin.buffer.read().split()
    size = int(tokens[0])
    swap_count = int(tokens[1])
    values = [int(token) for token in tokens[2 : 2 + size]]

    # Swaps are freely composable, so within one connected component the values
    # can be permuted arbitrarily. Placing them largest-first at the smallest
    # positions therefore gives the lexicographic maximum.
    positions = DisjointSet(size)
    cursor = 2 + size
    for _ in range(swap_count):
        positions.union(int(tokens[cursor]), int(tokens[cursor + 1]))
        cursor += 2

    component_positions = defaultdict(list)
    for position in range(1, size + 1):
        component_positions[positions.find(position)].append(position)

    answer = [0] * (size + 1)
    for member_positions in component_positions.values():
        available = sorted((values[position - 1] for position in member_positions), reverse=True)
        for position, value in zip(member_positions, available):
            answer[position] = value

    print(" ".join(map(str, answer[1:])))


if __name__ == "__main__":
    main()