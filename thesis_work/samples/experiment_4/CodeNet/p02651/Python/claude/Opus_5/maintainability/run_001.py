"""Decide whether the first player can win the XOR-basis game."""

import sys

BIT_WIDTH = 64
FIRST_PLAYER_WINS = 1
FIRST_PLAYER_LOSES = 0
FIRST_PLAYER_MARK = "1"


def first_player_wins(values: list[int], owners: str) -> int:
    """True when the first player owns a value that is linearly independent.

    Values are folded into a linear basis over GF(2) from the back of the list
    forwards. The first value that survives reduction is the deciding one: if
    the first player owns it, they can force the win.
    """
    basis = [0] * BIT_WIDTH

    for index in reversed(range(len(values))):
        residue = values[index]

        for bit in reversed(range(BIT_WIDTH)):
            if not (residue >> bit) & 1:
                continue
            if basis[bit] == 0:
                basis[bit] = residue
                return (
                    FIRST_PLAYER_WINS
                    if owners[index] == FIRST_PLAYER_MARK
                    else FIRST_PLAYER_LOSES
                )
            residue ^= basis[bit]

    return FIRST_PLAYER_LOSES


def main() -> None:
    tokens = sys.stdin.buffer.read().split()
    cursor = 0
    test_count = int(tokens[cursor])
    cursor += 1

    verdicts = []
    for _ in range(test_count):
        value_count = int(tokens[cursor])
        cursor += 1
        values = [int(token) for token in tokens[cursor : cursor + value_count]]
        cursor += value_count
        owners = tokens[cursor].decode()
        cursor += 1

        verdicts.append(first_player_wins(values, owners))

    sys.stdout.write("\n".join(map(str, verdicts)) + "\n")


if __name__ == "__main__":
    main()