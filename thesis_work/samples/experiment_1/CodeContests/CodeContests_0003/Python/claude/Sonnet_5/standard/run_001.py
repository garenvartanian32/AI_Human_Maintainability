import sys

def main():
    data = sys.stdin.read().split()
    idx = 0
    n = int(data[idx]); idx += 1
    deg = [0]*(n+1)
    for _ in range(n-1):
        u = int(data[idx]); v = int(data[idx+1]); idx += 2
        deg[u] += 1
        deg[v] += 1
    ok = all(d != 2 for d in deg[1:])
    print("YES" if ok else "NO")

main()