"""Shortest king's walk across a sparse set of allowed board squares."""

import sys
from collections import deque

UNREACHABLE = -1

KING_MOVES = [
    (-1, -1), (-1, 0), (-1, 1),
    (0, -1),           (0, 1),
    (1, -1),  (1, 0),  (1, 1),
]


def shortest_walk(
    start: tuple[int, int], goal: tuple[int, int], allowed: set[tuple[int, int]]
) -> int:
    """Breadth-first search over the allowed squares only."""
    if start not in allowed or goal not in allowed:
        return UNREACHABLE

    steps_to = {start: 0}
    frontier = deque([start])

    while frontier:
        square = frontier.popleft()
        if square == goal:
            return steps_to[square]

        row, column = square
        for row_delta, column_delta in KING_MOVES:
            neighbour = (row + row_delta, column + column_delta)
            if neighbour in allowed and neighbour not in steps_to:
                steps_to[neighbour] = steps_to[square] + 1
                frontier.append(neighbour)

    return UNREACHABLE


def main() -> None:
    tokens = sys.stdin.buffer.read().split()
    start = (int(tokens[0]), int(tokens[1]))
    goal = (int(tokens[2]), int(tokens[3]))
    segment_count = int(tokens[4])

    # The board is given as horizontal segments, which is far smaller than the
    # full coordinate range; only these squares may be stepped on.
    allowed: set[tuple[int, int]] = set()
    cursor = 5
    for _ in range(segment_count):
        row = int(tokens[cursor])
        first_column = int(tokens[cursor + 1])
        last_column = int(tokens[cursor + 2])
        cursor += 3
        for column in range(first_column, last_column + 1):
            allowed.add((row, column))

    print(shortest_walk(start, goal, allowed))


if __name__ == "__main__":
    main()