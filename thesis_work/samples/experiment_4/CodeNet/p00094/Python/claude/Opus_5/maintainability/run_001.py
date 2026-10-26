"""Convert a room's area from square metres to tsubo."""

import sys

SQUARE_METRES_PER_TSUBO = 3.305785
DECIMAL_PLACES = 6


def main() -> None:
    width, depth = map(float, sys.stdin.read().split())

    area_in_tsubo = (width * depth) / SQUARE_METRES_PER_TSUBO
    print(f"{area_in_tsubo:.{DECIMAL_PLACES}f}")


if __name__ == "__main__":
    main()