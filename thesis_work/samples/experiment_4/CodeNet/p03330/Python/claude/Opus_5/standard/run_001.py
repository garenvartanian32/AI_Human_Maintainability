import sys
from itertools import permutations

def main():
    data = sys.stdin.read().split()
    idx = 0
    n = int(data[idx]); c = int(data[idx+1]); idx += 2
    D = []
    for i in range(c):
        row = [int(data[idx+j]) for j in range(c)]
        idx += c
        D.append(row)
    count = [[0]*c for _ in range(3)]
    for i in range(n):
        for j in range(n):
            col = int(data[idx]); idx += 1
            g = (i + j) % 3
            count[g][col-1] += 1

    cost = [[0]*c for _ in range(3)]
    for g in range(3):
        for y in range(c):
            s = 0
            for x in range(c):
                s += count[g][x] * D[x][y]
            cost[g][y] = s

    best = None
    for y0 in range(c):
        for y1 in range(c):
            if y1 == y0:
                continue
            for y2 in range(c):
                if y2 == y0 or y2 == y1:
                    continue
                total = cost[0][y0] + cost[1][y1] + cost[2][y2]
                if best is None or total < best:
                    best = total
    print(best)

main()