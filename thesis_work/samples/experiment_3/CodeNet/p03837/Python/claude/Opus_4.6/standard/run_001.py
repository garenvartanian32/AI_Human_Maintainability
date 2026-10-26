import sys

def main():
    data = sys.stdin.read().split()
    idx = 0
    n = int(data[idx]); m = int(data[idx+1]); idx += 2
    INF = float('inf')
    dist = [[INF]*(n+1) for _ in range(n+1)]
    for i in range(1, n+1):
        dist[i][i] = 0
    edges = []
    for _ in range(m):
        a = int(data[idx]); b = int(data[idx+1]); c = int(data[idx+2]); idx += 3
        edges.append((a, b, c))
        if c < dist[a][b]:
            dist[a][b] = c
            dist[b][a] = c
    for k in range(1, n+1):
        for i in range(1, n+1):
            dik = dist[i][k]
            if dik == INF:
                continue
            for j in range(1, n+1):
                nd = dik + dist[k][j]
                if nd < dist[i][j]:
                    dist[i][j] = nd
    count = 0
    for a, b, c in edges:
        if dist[a][b] < c:
            count += 1
    print(count)

main()