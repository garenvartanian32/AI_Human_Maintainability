import sys

MOD = 10**9+7
MAXM = 200001

def main():
    data = sys.stdin.read().split()
    idx = 0
    t = int(data[idx]); idx += 1
    length = [[0]*(MAXM) for _ in range(10)]
    for d in range(10):
        length[d][0] = 1
    for s in range(1, MAXM):
        for d in range(10):
            nd = d+1
            digs = [int(c) for c in str(nd)]
            length[d][s] = sum(length[dd][s-1] for dd in digs) % MOD
    out = []
    for _ in range(t):
        n, m = data[idx], int(data[idx+1]); idx += 2
        total = 0
        for c in n:
            dd = int(c)
            total = (total + length[dd][m]) % MOD
        out.append(str(total))
    print('\n'.join(out))

main()