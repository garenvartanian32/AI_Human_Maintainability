"""Score two teams by summing each one's three best results."""

import sys

TEAM_SIZE = 10
COUNTED_RESULTS = 3


def team_score(results: list[int]) -> int:
    """Sum of the three highest results for one team."""
    return sum(sorted(results, reverse=True)[:COUNTED_RESULTS])


def main() -> None:
    values = [int(token) for token in sys.stdin.buffer.read().split()]

    west_results = values[:TEAM_SIZE]
    east_results = values[TEAM_SIZE : 2 * TEAM_SIZE]

    print(team_score(west_results), team_score(east_results))


if __name__ == "__main__":
    main()