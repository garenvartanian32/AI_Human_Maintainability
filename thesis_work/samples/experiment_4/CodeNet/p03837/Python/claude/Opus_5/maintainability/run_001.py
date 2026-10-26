"""Count roads that lie on no shortest path between their two towns."""

import sys

UNREACHABLE = float("inf")


def main() -> None:
    tokens = sys.stdin.buffer.read().split()
    town_count = int(tokens[0])
    road_count = int(tokens[1])

    shortest = [[UNREACHABLE] * (town_count + 1) for _ in range(town_count + 1)]
    for town in range(1, town_count + 1):
        shortest[town][town] = 0

    roads = []
    cursor = 2
    for _ in range(road_count):
        first = int(tokens[cursor])
        second = int(tokens[cursor + 1])
        length = int(tokens[cursor + 2])
        cursor += 3

        roads.append((first, second, length))
        # Parallel roads may exist; keep only the shortest as the direct link.
        if length < shortest[first][second]:
            shortest[first][second] = length
            shortest[second][first] = length

    _run_floyd_warshall(shortest, town_count)

    # A road is redundant exactly when some detour beats travelling along it.
    redundant = sum(1 for first, second, length in roads if shortest[first][second] < length)
    print(redundant)


def _run_floyd_warshall(shortest: list[list[float]], town_count: int) -> None:
    for waypoint in range(1, town_count + 1):
        via_waypoint = shortest[waypoint]
        for origin in range(1, town_count + 1):
            to_waypoint = shortest[origin][waypoint]
            if to_waypoint == UNREACHABLE:
                continue

            row = shortest[origin]
            for destination in range(1, town_count + 1):
                detour = to_waypoint + via_waypoint[destination]
                if detour < row[destination]:
                    row[destination] = detour


if __name__ == "__main__":
    main()