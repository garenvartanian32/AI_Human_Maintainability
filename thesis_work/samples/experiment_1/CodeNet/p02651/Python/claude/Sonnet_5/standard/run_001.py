import sys

def solve(n, A, S):
    basis = [0]*64
    last_pivot_owner = None
    for i in range(n-1, -1, -1):
        cur = A[i]
        for b in range(63, -1, -1):
            if not (cur >> b) & 1:
                continue
            if basis[b] == 0:
                basis[b] = cur
                last_pivot_owner = S[i]
                cur = 0
                break
            else:
                cur ^= basis[b]
    return last_pivot_owner

def main():
    data = sys.stdin.read().split('\n')
    idx = 0
    t = int(data[idx].strip()); idx += 1
    out = []
    for _ in range(t):
        n = int(data[idx].strip()); idx += 1
        A = list(map(int, data[idx].split())); idx += 1
        S = data[idx].strip(); idx += 1
        out.append(solve(n, A, S))
    print('\n'.join(out))

main()