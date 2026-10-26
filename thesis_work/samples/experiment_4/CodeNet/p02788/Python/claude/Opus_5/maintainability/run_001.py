"""Fewest bombs needed to clear a line of monsters."""

import sys
from bisect import bisect_right


def main() -> None:
    tokens = sys.stdin.buffer.read().split()
    monster_count = int(tokens[0])
    blast_radius = int(tokens[1])
    blast_damage = int(tokens[2])

    monsters = []
    cursor = 3
    for _ in range(monster_count):
        monsters.append((int(tokens[cursor]), int(tokens[cursor + 1])))
        cursor += 2
    monsters.sort()

    positions = [position for position, _ in monsters]

    # Sweeping left to right, every monster must die at its own turn, so bombs
    # are always centred as far right as still covers it. `expiring` records
    # where each blast stops contributing, letting us carry a running total.
    expiring = [0] * (monster_count + 1)
    damage_in_effect = 0
    bombs_used = 0

    for index, (position, health) in enumerate(monsters):
        damage_in_effect -= expiring[index]

        remaining_health = health - damage_in_effect
        if remaining_health <= 0:
            continue

        bombs = -(-remaining_health // blast_damage)
        bombs_used += bombs
        damage_in_effect += bombs * blast_damage

        last_covered = bisect_right(positions, position + 2 * blast_radius)
        if last_covered <= monster_count:
            expiring[last_covered] += bombs * blast_damage

    print(bombs_used)


if __name__ == "__main__":
    main()