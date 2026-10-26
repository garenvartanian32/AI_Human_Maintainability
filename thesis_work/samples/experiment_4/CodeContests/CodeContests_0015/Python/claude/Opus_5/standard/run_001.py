import sys

MOD = 10 ** 9 + 7
MAXM = 200001

def build_L():
    L = [0] * (MAXM + 1)
    L[0] = 1
    for t in range(1, MAXM + 1):
        term1 = 1 if t <= 8 else L[t - 9]
        term2 = 1 if t <= 9 else L[t - 10]
        L[t] = (term1 + term2) % MOD
    return L

def main():
    data = sys.stdin.buffer.read().split()
    idx = 0
    t = int(data[idx]); idx += 1
    L = build_L()
    out = []
    for _ in range(t):
        n = data[idx].decode(); m = int(data[idx+1]); idx += 2
        total = 0
        for ch in n:
            d = int(ch)
            rem = 9 - d
            if m < rem:
                total += 1
            else:
                total += L[m - rem]
            total %= MOD
        out.append(str(total))
    print('\n'.join(out))

main()