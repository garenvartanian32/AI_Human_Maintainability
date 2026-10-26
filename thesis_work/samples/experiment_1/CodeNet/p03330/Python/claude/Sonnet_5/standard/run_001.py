import sys

def main():
    data = sys.stdin.read().split()
    idx = 0
    n = int(data[idx]); c = int(data[idx+1]); idx += 2
    D = [[0]*(c+1) for _ in range(c+1)]
    for i in range(1, c+1):
        for j in range(1, c+1):
            D[i][j] = int(data[idx]); idx += 1
    grid = []
    for i in range(n):
        row = [int(data[idx+k]) for k in range(n)]
        idx += n
        grid.append(row)
    hist = [[0]*(c+1) for _ in range(3)]
    for i in range(n):
        for j in range(n):
            g = ((i+1)+(j+1)) % 3
            hist[g][grid[i][j]] += 1
    cost = [[0]*(c+1) for _ in range(3)]
    for g in range(3):
        for k in range(1, c+1):
            s = 0
            for col in range(1, c+1):
                s += hist[g][col] * D[col][k]
            cost[g][k] = s
    best = float('inf')
    for k0 in range(1, c+1):
        for k1 in range(1, c+1):
            if k1 == k0:
                continue
            for k2 in range(1, c+1):
                if k2 == k0 or k2 == k1:
                    continue
                total = cost[0][k0] + cost[1][k1] + cost[2][k2]
                best = min(best, total)
    print(best)

main()