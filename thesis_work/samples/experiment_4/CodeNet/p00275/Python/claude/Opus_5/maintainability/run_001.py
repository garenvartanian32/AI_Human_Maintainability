"""Simulate the bozu-mekuri card game and report each player's final hand."""

import sys

END_OF_INPUT = 0
MONK = "S"      # sends the player's whole hand to the field
PRINCESS = "L"  # sweeps the field into the player's hand


def play(player_count: int, cards: str) -> tuple[list[int], int]:
    """Deal the cards in turn; return sorted hand sizes and the field size."""
    hands = [0] * player_count
    field = 0

    for turn, card in enumerate(cards):
        player = turn % player_count

        if card == MONK:
            field += hands[player] + 1
            hands[player] = 0
        elif card == PRINCESS:
            hands[player] += field + 1
            field = 0
        else:
            hands[player] += 1

    hands.sort()
    return hands, field


def main() -> None:
    tokens = sys.stdin.buffer.read().split()
    cursor = 0
    results = []

    while cursor < len(tokens):
        player_count = int(tokens[cursor])
        cursor += 1
        if player_count == END_OF_INPUT:
            break

        cards = tokens[cursor].decode()
        cursor += 1

        hands, field = play(player_count, cards)
        results.append(" ".join(map(str, hands + [field])))

    sys.stdout.write("\n".join(results) + "\n")


if __name__ == "__main__":
    main()