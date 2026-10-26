"""Total candles burned when spent stubs can be recycled into new candles."""

import sys


def total_candles_burned(initial_candles: int, stubs_per_candle: int) -> int:
    """Return how many candles burn in total, recycling stubs as we go."""
    burned = initial_candles
    stubs_on_hand = initial_candles

    while stubs_on_hand >= stubs_per_candle:
        recycled = stubs_on_hand // stubs_per_candle
        burned += recycled
        # The recycled candles burn down into stubs of their own.
        stubs_on_hand = stubs_on_hand % stubs_per_candle + recycled

    return burned


def main() -> None:
    initial_candles, stubs_per_candle = map(int, sys.stdin.buffer.read().split())
    print(total_candles_burned(initial_candles, stubs_per_candle))


if __name__ == "__main__":
    main()