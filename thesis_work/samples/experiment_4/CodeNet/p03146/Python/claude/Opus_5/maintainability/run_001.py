"""First index at which a Collatz-style sequence revisits an earlier term."""

import sys


def next_term(value: int) -> int:
    """Halve an even term; otherwise triple it and add one."""
    return value // 2 if value % 2 == 0 else 3 * value + 1


def main() -> None:
    start = int(sys.stdin.read().strip())

    # The sequence is deterministic, so the first repeated value is the point
    # at which it has entered a cycle.
    seen = {start}
    value = start
    index = 1

    while True:
        value = next_term(value)
        index += 1
        if value in seen:
            print(index)
            return
        seen.add(value)


if __name__ == "__main__":
    main()