while 1:
    n = int(input())
    if n == 0:
        break

    data = list(input())

    ba = 0
    hand = [0] * n
    for i in range(100):
        turn = i % n
        card = data[i]
        if card == "M":
            hand[turn] += 1
        elif card == "S":
            ba += hand[turn] + 1
            hand[turn] = 0
        else:
            hand[turn] += ba + 1
            ba = 0

    hand.sort()
    hand.append(ba)
    print(' '.join(str(h) for h in hand))

