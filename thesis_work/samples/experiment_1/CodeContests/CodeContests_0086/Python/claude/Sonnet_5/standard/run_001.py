import sys

def main():
    data = sys.stdin.read().split()
    idx = 0
    n, m = int(data[idx]), int(data[idx+1]); idx += 2
    p = [int(data[idx+i]) for i in range(n)]; idx += n
    parent = list(range(n+1))
    def find(x):
        while parent[x] != x:
            parent[x] = parent[parent[x]]
            x = parent[x]
        return x
    for _ in range(m):
        a, b = int(data[idx]), int(data[idx+1]); idx += 2
        ra, rb = find(a), find(b)
        if ra != rb:
            parent[ra] = rb
    groups = {}
    for i in range(1, n+1):
        r = find(i)
        groups.setdefault(r, []).append(i)
    result = p[:]
    for r, idxs in groups.items():
        vals = sorted([p[i-1] for i in idxs], reverse=True)
        idxs_sorted = sorted(idxs)
        for pos, v in zip(idxs_sorted, vals):
            result[pos-1] = v
    print(' '.join(map(str, result)))

main()