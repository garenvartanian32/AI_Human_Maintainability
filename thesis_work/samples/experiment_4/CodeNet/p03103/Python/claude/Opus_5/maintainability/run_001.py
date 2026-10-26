"""Cheapest way to buy a fixed number of drinks across several shops."""

import sys


def main() -> None:
    tokens = sys.stdin.buffer.read().split()
    shop_count = int(tokens[0])
    drinks_wanted = int(tokens[1])

    shops = []
    cursor = 2
    for _ in range(shop_count):
        price = int(tokens[cursor])
        stock = int(tokens[cursor + 1])
        cursor += 2
        shops.append((price, stock))

    # Drinks are interchangeable, so exhausting the cheapest shop before moving
    # on is optimal.
    shops.sort()

    total_cost = 0
    still_needed = drinks_wanted
    for price, stock in shops:
        if still_needed == 0:
            break
        bought = min(stock, still_needed)
        total_cost += bought * price
        still_needed -= bought

    print(total_cost)


if __name__ == "__main__":
    main()