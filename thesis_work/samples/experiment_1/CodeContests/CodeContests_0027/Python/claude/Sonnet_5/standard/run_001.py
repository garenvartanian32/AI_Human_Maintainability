import sys

def main():
    data = sys.stdin.read().split()
    idx = 0
    n = int(data[idx]); m = int(data[idx+1]); idx += 2
    on = set()
    for _ in range(n):
        x = int(data[idx]); idx += 1
        for _ in range(x):
            on.add(int(data[idx])); idx += 1
    print("YES" if len(on) == m else "NO")

main()