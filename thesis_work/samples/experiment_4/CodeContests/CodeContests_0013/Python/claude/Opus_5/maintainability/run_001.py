"""Size and minimal basis of the span of sparse GF(2) vectors."""

import sys

MODULUS = 10**9 + 7
GROUND = 0  # virtual node standing in for "no second coordinate"


class DisjointSet:
    """Union-find over coordinate nodes, with path compression."""

    def __init__(self, size: int) -> None:
        self._parent = list(range(size + 1))

    def find(self, node: int) -> int:
        root = node
        while self._parent[root] != root:
            root = self._parent[root]
        while self._parent[node] != root:
            self._parent[node], node = root, self._parent[node]
        return root

    def union(self, first: int, second: int) -> bool:
        """Merge the two components; return True when they were distinct."""
        first_root, second_root = self.find(first), self.find(second)
        if first_root == second_root:
            return False
        self._parent[first_root] = second_root
        return True


def main() -> None:
    tokens = sys.stdin.buffer.read().split()
    vector_count = int(tokens[0])
    coordinate_count = int(tokens[1])

    # Each vector has one or two set coordinates, so it behaves like an edge in
    # a graph. Independence over GF(2) is then exactly acyclicity, which
    # union-find decides directly.
    components = DisjointSet(coordinate_count)
    basis_indices: list[int] = []

    cursor = 2
    for vector_index in range(1, vector_count + 1):
        set_coordinates = int(tokens[cursor])
        cursor += 1

        if set_coordinates == 1:
            first, second = GROUND, int(tokens[cursor])
            cursor += 1
        else:
            first, second = int(tokens[cursor]), int(tokens[cursor + 1])
            cursor += 2

        if components.union(first, second):
            basis_indices.append(vector_index)

    span_size = pow(2, len(basis_indices), MODULUS)
    print(span_size, len(basis_indices))
    print(" ".join(map(str, basis_indices)))


if __name__ == "__main__":
    main()