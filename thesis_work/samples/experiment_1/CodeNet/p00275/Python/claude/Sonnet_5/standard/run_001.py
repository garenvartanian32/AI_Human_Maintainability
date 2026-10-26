import sys

def main():
    data = sys.stdin.read().split('\n')
    idx = 0
    out = []
    while True:
        line = data[idx].strip(); idx += 1
        n = int(line)
        if n == 0:
            break
        cards = data[idx].strip(); idx += 1
        hands = [0]*n
        field = 0
        for i, c in enumerate(cards):
            p = i % n
            if c == 'M':
                hands[p] += 1
            elif c == 'S':
                field += hands[p] + 1
                hands[p] = 0
            elif c == 'L':
                hands[p] += field + 1
                field = 0
        hands.sort()
        out.append(' '.join(map(str, hands)) + ' ' + str(field))
    print('\n'.join(out))

main()