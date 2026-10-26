"""Smallest candidate total that beats every rival and every existing score."""

import sys


def main() -> None:
    tokens = sys.stdin.buffer.read().split()
    count = int(tokens[0])
    scores = [int(token) for token in tokens[1 : 1 + count]]

    # The answer must be at least the current maximum, and large enough that
    # `count` copies of it strictly exceed twice the current total.
    total = sum(scores)
    smallest_winning = max(scores)
    while count * smallest_winning <= 2 * total:
        smallest_winning += 1

    print(smallest_winning)


if __name__ == "__main__":
    main()