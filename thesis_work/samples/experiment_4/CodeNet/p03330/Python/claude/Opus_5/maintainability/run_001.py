"""Cheapest recolouring of a grid so that colours depend on (row+col) mod 3."""

import sys
from itertools import permutations

GROUP_COUNT = 3


def main() -> None:
    tokens = sys.stdin.buffer.read().split()
    grid_size = int(tokens[0])
    colour_count = int(tokens[1])
    cursor = 2

    repaint_cost = []
    for _ in range(colour_count):
        repaint_cost.append([int(token) for token in tokens[cursor : cursor + colour_count]])
        cursor += colour_count

    # Cells sharing (row + column) mod 3 must end up the same colour, so only
    # the tally of current colours per group matters.
    tally = [[0] * colour_count for _ in range(GROUP_COUNT)]
    for row in range(grid_size):
        for column in range(grid_size):
            current_colour = int(tokens[cursor]) - 1
            cursor += 1
            tally[(row + column) % GROUP_COUNT][current_colour] += 1

    # Cost of turning every cell of a group into one target colour.
    group_cost = [
        [
            sum(tally[group][source] * repaint_cost[source][target]
                for source in range(colour_count))
            for target in range(colour_count)
        ]
        for group in range(GROUP_COUNT)
    ]

    # The three groups must receive three distinct colours.
    print(min(
        group_cost[0][first] + group_cost[1][second] + group_cost[2][third]
        for first, second, third in permutations(range(colour_count), GROUP_COUNT)
    ))


if __name__ == "__main__":
    main()