import sys

def main():
    data = sys.stdin.buffer.read().split()
    idx = 0
    n = int(data[idx]); m = int(data[idx+1]); idx += 2
    p = [int(data[idx+i]) for i in range(n)]; idx += n
    parent = list(range(n+1))

    def find(x):
        while parent[x] != x:
            parent[x] = parent[parent[x]]
            x = parent[x]
        return x

    for _ in range(m):
        aj = int(data[idx]); bj = int(data[idx+1]); idx += 2
        ra, rb = find(aj), find(bj)
        if ra != rb:
            parent[ra] = rb

    groups = {}
    for i in range(1, n+1):
        r = find(i)
        groups.setdefault(r, []).append(i)

    ans = [0]*(n+1)
    for r, positions in groups.items():
        positions_sorted = sorted(positions)
        values = sorted((p[i-1] for i in positions_sorted), reverse=True)
        for pos, val in zip(positions_sorted, values):
            ans[pos] = val

    print(' '.join(map(str, ans[1:n+1])))

main()