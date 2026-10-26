import sys

MOD = 10**9+7

def main():
    data = sys.stdin.read().split()
    idx = 0
    n = int(data[idx]); m = int(data[idx+1]); idx += 2
    parent = list(range(m+1))
    def find(x):
        while parent[x] != x:
            parent[x] = parent[parent[x]]
            x = parent[x]
        return x
    def union(x, y):
        rx, ry = find(x), find(y)
        if rx != ry:
            parent[rx] = ry
            return True
        return False
    single_seen = set()
    rank_count = 0
    chosen = []
    for i in range(n):
        vec = data[idx]; idx += 1
        ones = [j+1 for j, c in enumerate(vec) if c == '1']
        if len(ones) == 0:
            continue
        elif len(ones) == 1:
            v = ones[0]
            if v not in single_seen:
                single_seen.add(v)
                rank_count += 1
                chosen.append(i+1)
                # merge into a virtual "zero" root separately; treat singles as forcing that coordinate fixed
                parent[v] = 0 if False else parent[v]
        else:
            a, b = ones[0], ones[1]
            if union(a, b):
                rank_count += 1
                chosen.append(i+1)
    size = pow(2, rank_count, MOD)
    print(size)
    print(len(chosen))
    print(' '.join(map(str, chosen)))

main()