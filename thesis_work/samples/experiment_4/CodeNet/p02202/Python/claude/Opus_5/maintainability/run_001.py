"""Total happiness once each gag is discounted by its position in the show."""

import sys


def main() -> None:
    tokens = sys.stdin.buffer.read().split()
    gag_count = int(tokens[0])
    values = [int(token) for token in tokens[1 : 1 + gag_count]]

    # The nth gag performed loses n happiness, so ordering never matters:
    # the penalties are always 1, 2, ..., gag_count in some assignment.
    total_value = sum(values)
    total_penalty = gag_count * (gag_count + 1) // 2

    print(total_value - total_penalty)


if __name__ == "__main__":
    main()