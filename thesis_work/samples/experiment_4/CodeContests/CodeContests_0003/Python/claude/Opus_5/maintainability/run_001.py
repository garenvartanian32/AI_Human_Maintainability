"""Decide whether a tree admits the required edge configuration."""

import sys

DEGREE_THAT_BLOCKS = 2


def main() -> None:
    tokens = sys.stdin.buffer.read().split()
    vertex_count = int(tokens[0])

    degrees = [0] * (vertex_count + 1)
    cursor = 1
    for _ in range(vertex_count - 1):
        first_endpoint = int(tokens[cursor])
        second_endpoint = int(tokens[cursor + 1])
        cursor += 2
        degrees[first_endpoint] += 1
        degrees[second_endpoint] += 1

    # A vertex of degree exactly two cannot be satisfied, so its presence
    # rules the whole configuration out.
    has_blocking_vertex = any(
        degrees[vertex] == DEGREE_THAT_BLOCKS for vertex in range(1, vertex_count + 1)
    )

    print("NO" if has_blocking_vertex else "YES")


if __name__ == "__main__":
    main()