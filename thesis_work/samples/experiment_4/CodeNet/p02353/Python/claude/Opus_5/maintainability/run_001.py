"""Range Update Query plus Range Sum Query, backed by a lazy segment tree."""

import sys

UPDATE_COMMAND = 0


class LazySegmentTree:
    """Segment tree supporting assign-on-range and sum-over-range."""

    def __init__(self, length: int) -> None:
        self._width = 1
        while self._width < length:
            self._width *= 2

        self._sums = [0] * (2 * self._width)
        self._pending = [None] * (2 * self._width)

    def assign(self, first: int, last: int, value: int) -> None:
        """Set every element in [first, last] to ``value``."""
        self._assign(1, 0, self._width - 1, first, last, value)

    def total(self, first: int, last: int) -> int:
        """Return the sum over [first, last]."""
        return self._total(1, 0, self._width - 1, first, last)

    def _push_down(self, node: int, span: int) -> None:
        """Hand a pending assignment down to both children."""
        value = self._pending[node]
        if value is None:
            return

        half = span // 2
        for child in (2 * node, 2 * node + 1):
            self._sums[child] = value * half
            self._pending[child] = value
        self._pending[node] = None

    def _assign(self, node: int, low: int, high: int, first: int, last: int, value: int) -> None:
        if last < low or high < first:
            return
        if first <= low and high <= last:
            self._sums[node] = value * (high - low + 1)
            self._pending[node] = value
            return

        self._push_down(node, high - low + 1)
        middle = (low + high) // 2
        self._assign(2 * node, low, middle, first, last, value)
        self._assign(2 * node + 1, middle + 1, high, first, last, value)
        self._sums[node] = self._sums[2 * node] + self._sums[2 * node + 1]

    def _total(self, node: int, low: int, high: int, first: int, last: int) -> int:
        if last < low or high < first:
            return 0
        if first <= low and high <= last:
            return self._sums[node]

        self._push_down(node, high - low + 1)
        middle = (low + high) // 2
        return (
            self._total(2 * node, low, middle, first, last)
            + self._total(2 * node + 1, middle + 1, high, first, last)
        )


def main() -> None:
    sys.setrecursionlimit(300_000)
    tokens = sys.stdin.buffer.read().split()

    length = int(tokens[0])
    query_count = int(tokens[1])
    tree = LazySegmentTree(length)

    answers = []
    cursor = 2
    for _ in range(query_count):
        command = int(tokens[cursor])
        cursor += 1

        if command == UPDATE_COMMAND:
            first, last, value = (int(tokens[cursor + offset]) for offset in range(3))
            cursor += 3
            tree.assign(first, last, value)
        else:
            first, last = int(tokens[cursor]), int(tokens[cursor + 1])
            cursor += 2
            answers.append(tree.total(first, last))

    sys.stdout.write("\n".join(map(str, answers)) + "\n")


if __name__ == "__main__":
    main()