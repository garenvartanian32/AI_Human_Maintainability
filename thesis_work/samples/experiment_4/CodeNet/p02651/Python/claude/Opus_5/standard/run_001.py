import sys

def solve(n, a, s):
    basis = {}  # leadbit -> value
    for i in range(n-1, -1, -1):
        v = a[i]
        for bit in range(63, -1, -1):
            if (v >> bit) & 1:
                if bit in basis:
                    v ^= basis[bit]
                else:
                    break
        if v != 0:
            lb = v.bit_length() - 1
            basis[lb] = v
            if s[i] == '1':
                return 1
    return 0

def main():
    data = sys.stdin.read().split()
    idx = 0
    t = int(data[idx]); idx += 1
    out = []
    for _ in range(t):
        n = int(data[idx]); idx += 1
        a = [int(data[idx+i]) for i in range(n)]; idx += n
        s = data[idx]; idx += 1
        out.append(str(solve(n, a, s)))
    print('\n'.join(out))

main()