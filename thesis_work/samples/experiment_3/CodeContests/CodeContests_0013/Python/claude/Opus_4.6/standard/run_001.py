import sys

def main():
    data = sys.stdin.buffer.read().split()
    idx = 0
    n = int(data[idx]); m = int(data[idx+1]); idx += 2
    parent = list(range(m + 1))

    def find(x):
        while parent[x] != x:
            parent[x] = parent[parent[x]]
            x = parent[x]
        return x

    basis = []
    for i in range(1, n + 1):
        kk = int(data[idx]); idx += 1
        if kk == 1:
            u = 0
            v = int(data[idx]); idx += 1
        else:
            u = int(data[idx]); v = int(data[idx+1]); idx += 2
        ru, rv = find(u), find(v)
        if ru != rv:
            parent[ru] = rv
            basis.append(i)

    MOD = 10 ** 9 + 7
    rank = len(basis)
    total = pow(2, rank, MOD)
    print(total, rank)
    print(' '.join(map(str, basis)))

main()