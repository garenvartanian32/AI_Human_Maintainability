"""Build two Hamiltonian paths that avoid forbidden endpoint adjacencies."""

import sys

IMPOSSIBLE = -1
MINIMUM_EDGES = 1  # one edge beyond n is always required


def main() -> None:
    tokens = sys.stdin.buffer.read().split()
    town_count = int(tokens[0])
    edge_budget = int(tokens[1])
    a, b, c, d = (int(tokens[index]) for index in range(2, 6))

    # With only four towns there is no room for a middle section, and the
    # construction below always spends exactly town_count + 1 edges.
    if town_count == 4 or edge_budget < town_count + MINIMUM_EDGES:
        print(IMPOSSIBLE)
        return

    endpoints = {a, b, c, d}
    middle = [town for town in range(1, town_count + 1) if town not in endpoints]

    # Both paths share the same middle spine but enter and leave it in a
    # different order, which keeps each forbidden pair non-adjacent.
    first_path = [a, c] + middle + [d, b]
    second_path = [c, a] + middle + [b, d]

    print(" ".join(map(str, first_path)))
    print(" ".join(map(str, second_path)))


if __name__ == "__main__":
    main()