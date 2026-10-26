"""Count the squares that lie on a cycle of a circular sugoroku board."""

import sys

UNVISITED = 0
ON_CURRENT_WALK = 1
FINISHED = 2


def main() -> None:
    tokens = sys.stdin.buffer.read().split()
    square_count = int(tokens[0])

    # Every square has exactly one successor, so the board is a functional
    # graph: each walk eventually runs into a cycle.
    successor = [
        (index + int(tokens[1 + index])) % square_count for index in range(square_count)
    ]

    state = [UNVISITED] * square_count
    lies_on_cycle = [False] * square_count

    for start in range(square_count):
        if state[start] != UNVISITED:
            continue

        walk: list[int] = []
        square = start
        while state[square] == UNVISITED:
            state[square] = ON_CURRENT_WALK
            walk.append(square)
            square = successor[square]

        # Re-entering the *current* walk means we closed a fresh cycle; the
        # cycle is the tail of the walk starting at the re-entry point.
        if state[square] == ON_CURRENT_WALK:
            cycle_start = walk.index(square)
            for member in walk[cycle_start:]:
                lies_on_cycle[member] = True

        for member in walk:
            state[member] = FINISHED

    print(sum(lies_on_cycle))


if __name__ == "__main__":
    main()