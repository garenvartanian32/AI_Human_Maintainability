import sys

def main():
    data = sys.stdin.read().split()
    idx = 0
    out = []
    while idx < len(data):
        n = int(data[idx]); idx += 1
        if n == 0:
            break
        cards = data[idx]; idx += 1
        hands = [0] * n
        field = 0
        for i, c in enumerate(cards):
            player = i % n
            if c == 'M':
                hands[player] += 1
            elif c == 'S':
                field += hands[player] + 1
                hands[player] = 0
            elif c == 'L':
                hands[player] += field + 1
                field = 0
        hands.sort()
        out.append(' '.join(map(str, hands)) + ' ' + str(field))
    print('\n'.join(out))

main()