import sys

def main():
    data = sys.stdin.read().split()
    idx = 0
    n = int(data[idx]); idx += 1
    deg = [0] * (n + 1)
    for _ in range(n - 1):
        u = int(data[idx]); v = int(data[idx+1]); idx += 2
        deg[u] += 1
        deg[v] += 1
    for i in range(1, n + 1):
        if deg[i] == 2:
            print("NO")
            return
    print("YES")

main()