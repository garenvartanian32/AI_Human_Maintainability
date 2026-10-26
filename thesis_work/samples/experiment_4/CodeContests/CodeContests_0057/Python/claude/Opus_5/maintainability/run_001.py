"""Largest x for which a binary matrix survives x-compression."""

import sys

HEX_BITS = 4


def decode_rows(hex_rows: list[str], size: int) -> list[list[int]]:
    """Expand each hexadecimal row into its ``size`` binary cells."""
    matrix = []
    for hex_row in hex_rows:
        bits: list[int] = []
        for character in hex_row:
            value = int(character, 16)
            for shift in reversed(range(HEX_BITS)):
                bits.append((value >> shift) & 1)
        matrix.append(bits)
    return matrix


def build_prefix_sums(matrix: list[list[int]], size: int) -> list[list[int]]:
    """Standard 2-D prefix sums, padded with a zero row and column."""
    prefix = [[0] * (size + 1) for _ in range(size + 1)]
    for row in range(1, size + 1):
        running = 0
        for column in range(1, size + 1):
            running += matrix[row - 1][column - 1]
            prefix[row][column] = prefix[row - 1][column] + running
    return prefix


def block_is_uniform(prefix: list[list[int]], top: int, left: int, side: int) -> bool:
    """True when the ``side``x``side`` block is entirely zeros or entirely ones."""
    bottom, right = top + side, left + side
    ones = (
        prefix[bottom][right]
        - prefix[top][right]
        - prefix[bottom][left]
        + prefix[top][left]
    )
    return ones == 0 or ones == side * side


def main() -> None:
    tokens = sys.stdin.buffer.read().split()
    size = int(tokens[0])
    hex_rows = [tokens[1 + index].decode() for index in range(size)]

    matrix = decode_rows(hex_rows, size)
    prefix = build_prefix_sums(matrix, size)

    # Larger x always implies every smaller divisor works, so scan downwards
    # and stop at the first divisor whose blocks are all uniform.
    for side in sorted((d for d in range(1, size + 1) if size % d == 0), reverse=True):
        if all(
            block_is_uniform(prefix, top, left, side)
            for top in range(0, size, side)
            for left in range(0, size, side)
        ):
            print(side)
            return


if __name__ == "__main__":
    main()