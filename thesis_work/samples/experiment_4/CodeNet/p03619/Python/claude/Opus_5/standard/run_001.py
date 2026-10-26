import sys
import math
import bisect

def main():
    data = sys.stdin.read().split()
    idx = 0
    x1 = int(data[idx]); y1 = int(data[idx+1]); x2 = int(data[idx+2]); y2 = int(data[idx+3]); idx += 4
    n = int(data[idx]); idx += 1
    fountains = []
    for _ in range(n):
        X = int(data[idx]); Y = int(data[idx+1]); idx += 2
        fountains.append((X, Y))

    r = 10.0
    dx = abs(x1 - x2)
    dy = abs(y1 - y2)
    manhattan = (dx + dy) * 100.0

    if x1 == x2 or y1 == y2:
        # straight line; check blocking fountain
        penalty = 0.0
        if y1 == y2:
            lo, hi = min(x1, x2), max(x1, x2)
            for X, Y in fountains:
                if Y == y1 and lo < X < hi:
                    penalty = r * (math.pi - 2)
                    break
        else:
            lo, hi = min(y1, y2), max(y1, y2)
            for X, Y in fountains:
                if X == x1 and lo < Y < hi:
                    penalty = r * (math.pi - 2)
                    break
        print(f"{manhattan + penalty:.15f}")
        return

    xlo, xhi = min(x1, x2), max(x1, x2)
    ylo, yhi = min(y1, y2), max(y1, y2)
    xdir = 1 if x2 > x1 else -1
    ydir = 1 if y2 > y1 else -1

    candidates = []
    for X, Y in fountains:
        if xlo < X < xhi and ylo < Y < yhi:
            candidates.append((X * xdir, Y * ydir))

    candidates.sort()
    # longest strictly increasing subsequence on the Y-transformed value
    tails = []
    for _, ty in candidates:
        pos = bisect.bisect_left(tails, ty)
        if pos == len(tails):
            tails.append(ty)
        else:
            tails[pos] = ty
    lis_len = len(tails)

    bonus = r * (2 - math.pi/2)
    answer = manhattan - lis_len * bonus
    print(f"{answer:.15f}")

main()