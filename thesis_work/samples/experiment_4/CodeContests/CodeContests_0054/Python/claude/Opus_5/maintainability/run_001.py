"""Selection sort, reporting the sorted array and the number of swaps made."""

import sys


def selection_sort(values: list[int]) -> int:
    """Sort ``values`` in place; return how many real swaps were performed."""
    swap_count = 0

    for boundary in range(len(values)):
        smallest = boundary
        for candidate in range(boundary + 1, len(values)):
            if values[candidate] < values[smallest]:
                smallest = candidate

        # Only count a swap when the element actually moves.
        if smallest != boundary:
            values[boundary], values[smallest] = values[smallest], values[boundary]
            swap_count += 1

    return swap_count


def main() -> None:
    tokens = sys.stdin.buffer.read().split()
    count = int(tokens[0])
    values = [int(token) for token in tokens[1 : 1 + count]]

    swap_count = selection_sort(values)

    print(" ".join(map(str, values)))
    print(swap_count)


if __name__ == "__main__":
    main()