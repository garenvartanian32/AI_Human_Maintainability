import sys

def main():
    data = sys.stdin.read().split()
    idx = 0
    out = []
    while idx < len(data):
        n = int(data[idx]); idx += 1
        if n == 0:
            break
        moves = data[idx:idx+n]; idx += n
        state = 0  # 0=FLOOR,1=RISING,2=PLATFORM,3=FALLING
        count = 0
        for mv in moves:
            direction = 'raise' if mv[1] == 'u' else 'lower'
            if state == 0:
                state = 1  # must be raise
            elif state == 1:
                if direction == 'raise':
                    state = 2
                    count += 1
                else:
                    state = 0
            elif state == 2:
                state = 3  # must be lower
            elif state == 3:
                if direction == 'lower':
                    state = 0
                    count += 1
                else:
                    state = 2
        out.append(str(count))
    print('\n'.join(out))

main()