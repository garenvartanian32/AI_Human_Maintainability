"""Rearrange a string to hold as many disjoint copies of b and c as possible."""

import sys
from collections import Counter

ALPHABET = "abcdefghijklmnopqrstuvwxyz"


def max_copies(available: Counter, needed: Counter) -> int:
    """How many whole copies of ``needed`` fit inside ``available``."""
    return min(available[letter] // count for letter, count in needed.items())


def main() -> None:
    lines = sys.stdin.read().split("\n")
    source, first_word, second_word = (line.strip() for line in lines[:3])

    available = Counter(source)
    need_first = Counter(first_word)
    need_second = Counter(second_word)

    # Copies of the two words compete for the same letters, so we simply try
    # every feasible count of the first word and take the best total.
    best_total = -1
    best_first_count = 0
    best_second_count = 0

    for first_count in range(max_copies(available, need_first) + 1):
        leftover = Counter(
            {letter: available[letter] - first_count * need_first[letter]
             for letter in available}
        )
        second_count = max_copies(leftover, need_second)

        if first_count + second_count > best_total:
            best_total = first_count + second_count
            best_first_count = first_count
            best_second_count = second_count

    used = Counter()
    for letter, count in need_first.items():
        used[letter] += best_first_count * count
    for letter, count in need_second.items():
        used[letter] += best_second_count * count

    # Any letters not consumed by a copy can be appended in any order.
    leftover_letters = "".join(
        letter * (available[letter] - used[letter]) for letter in sorted(available)
    )

    print(first_word * best_first_count + second_word * best_second_count + leftover_letters)


if __name__ == "__main__":
    main()