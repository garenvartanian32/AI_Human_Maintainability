import sys

def main():
    data = sys.stdin.buffer.read().split()
    idx = 0
    n = int(data[idx]); m = int(data[idx+1]); idx += 2
    covered = set()
    for _ in range(n):
        xi = int(data[idx]); idx += 1
        for _ in range(xi):
            covered.add(int(data[idx])); idx += 1
    print("YES" if len(covered) == m else "NO")

main()