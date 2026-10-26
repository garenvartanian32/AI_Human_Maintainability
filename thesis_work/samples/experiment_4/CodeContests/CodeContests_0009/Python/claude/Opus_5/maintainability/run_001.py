"""Cheapest way to give Alice and Bob each at least k liked books."""

import sys
from itertools import accumulate

NOT_POSSIBLE = -1


def prefix_sums(times: list[int]) -> list[int]:
    """Return cumulative reading times after sorting ascending."""
    times.sort()
    return [0] + list(accumulate(times))


def main() -> None:
    tokens = sys.stdin.buffer.read().split()
    book_count = int(tokens[0])
    required_likes = int(tokens[1])

    liked_by_both: list[int] = []
    liked_by_alice_only: list[int] = []
    liked_by_bob_only: list[int] = []

    cursor = 2
    for _ in range(book_count):
        reading_time = int(tokens[cursor])
        alice_likes = int(tokens[cursor + 1])
        bob_likes = int(tokens[cursor + 2])
        cursor += 3

        if alice_likes and bob_likes:
            liked_by_both.append(reading_time)
        elif alice_likes:
            liked_by_alice_only.append(reading_time)
        elif bob_likes:
            liked_by_bob_only.append(reading_time)

    both_prefix = prefix_sums(liked_by_both)
    alice_prefix = prefix_sums(liked_by_alice_only)
    bob_prefix = prefix_sums(liked_by_bob_only)

    # Choosing `shared` of the both-liked books covers that many likes for each
    # reader; the shortfall must be topped up from each single-reader pile.
    best_time = NOT_POSSIBLE
    for shared in range(len(liked_by_both) + 1):
        shortfall = max(required_likes - shared, 0)
        if shortfall > len(liked_by_alice_only) or shortfall > len(liked_by_bob_only):
            continue

        total_time = both_prefix[shared] + alice_prefix[shortfall] + bob_prefix[shortfall]
        if best_time == NOT_POSSIBLE or total_time < best_time:
            best_time = total_time

    print(best_time)


if __name__ == "__main__":
    main()