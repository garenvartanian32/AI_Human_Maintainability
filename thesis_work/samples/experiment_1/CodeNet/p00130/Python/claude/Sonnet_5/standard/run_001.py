import sys

def solve(s):
    chain = {0: s[0]}
    pos = 0
    i = 1
    n = len(s)
    while i < n:
        if s[i:i+2] == '->':
            pos += 1
            chain[pos] = s[i+2]
            i += 3
        else:
            pos -= 1
            chain[pos] = s[i]
            i += 1
    lo, hi = min(chain), max(chain)
    return ''.join(chain[p] for p in range(lo, hi+1))

def main():
    data = sys.stdin.read().split('\n')
    n = int(data[0])
    out = []
    for i in range(1, n+1):
        out.append(solve(data[i].strip()))
    print('\n'.join(out))

main()