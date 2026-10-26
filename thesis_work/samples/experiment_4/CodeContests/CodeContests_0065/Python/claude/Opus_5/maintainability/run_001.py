"""Decide the winner of the non-trivial-divisor game, and a winning first move."""

import sys

FIRST_PLAYER_WINS = 1
SECOND_PLAYER_WINS = 2
NO_MOVE_NEEDED = 0


def prime_factors_with_multiplicity(value: int) -> list[int]:
    """Return every prime factor of ``value``, repeated by its multiplicity."""
    factors: list[int] = []
    remaining = value

    divisor = 2
    while divisor * divisor <= remaining:
        while remaining % divisor == 0:
            factors.append(divisor)
            remaining //= divisor
        divisor += 1

    if remaining > 1:
        factors.append(remaining)
    return factors


def main() -> None:
    number = int(sys.stdin.buffer.read().split()[0])

    if number == 1:
        # Already a losing position for the opponent; nothing to play.
        print(FIRST_PLAYER_WINS)
        print(NO_MOVE_NEEDED)
        return

    factors = prime_factors_with_multiplicity(number)

    if len(factors) == 1:
        # A prime has no non-trivial divisor, so the opponent is stuck.
        print(FIRST_PLAYER_WINS)
        print(NO_MOVE_NEEDED)
    elif len(factors) == 2:
        # Any move hands a winning position to the opponent.
        print(SECOND_PLAYER_WINS)
    else:
        # Move to the product of the two smallest primes, a losing position.
        factors.sort()
        print(FIRST_PLAYER_WINS)
        print(factors[0] * factors[1])


if __name__ == "__main__":
    main()