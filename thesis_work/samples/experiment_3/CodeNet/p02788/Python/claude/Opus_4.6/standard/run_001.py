import sys
import bisect

def main():
    data = sys.stdin.read().split()
    idx = 0
    n = int(data[idx]); d = int(data[idx+1]); a = int(data[idx+2]); idx += 3
    monsters = []
    for _ in range(n):
        x = int(data[idx]); h = int(data[idx+1]); idx += 2
        monsters.append((x, h))
    monsters.sort()
    xs = [m[0] for m in monsters]
    hs = [m[1] for m in monsters]

    diff = [0] * (n + 1)
    current = 0
    total_bombs = 0
    for i in range(n):
        current += diff[i]
        remaining = hs[i] - current
        if remaining > 0:
            bombs = (remaining + a - 1) // a
            total_bombs += bombs
            current += bombs * a
            # find furthest index covered: X[r] <= xs[i] + 2*d
            limit = xs[i] + 2*d
            r = bisect.bisect_right(xs, limit) - 1
            if r + 1 <= n:
                diff[r+1] -= bombs * a
    print(total_bombs)

main()