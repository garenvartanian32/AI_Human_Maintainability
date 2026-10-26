"""Decide whether pressing every button can switch off all the bulbs."""

import sys


def main() -> None:
    tokens = sys.stdin.buffer.read().split()
    button_count = int(tokens[0])
    bulb_count = int(tokens[1])

    # Pressing every button is always allowed, so a bulb can be switched off
    # exactly when at least one button controls it.
    controlled_bulbs: set[int] = set()

    cursor = 2
    for _ in range(button_count):
        bulbs_on_this_button = int(tokens[cursor])
        cursor += 1
        for offset in range(bulbs_on_this_button):
            controlled_bulbs.add(int(tokens[cursor + offset]))
        cursor += bulbs_on_this_button

    every_bulb_is_covered = len(controlled_bulbs) == bulb_count
    print("YES" if every_bulb_is_covered else "NO")


if __name__ == "__main__":
    main()