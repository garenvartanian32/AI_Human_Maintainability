import sys

def main():
    data = sys.stdin.read().split()
    idx = 0
    n = int(data[idx]); k = int(data[idx+1]); idx += 2
    a = int(data[idx]); b = int(data[idx+1]); c = int(data[idx+2]); d = int(data[idx+3]); idx += 4

    if n == 4 or (n + 1) > k:
        print(-1)
        return

    special = {a, b, c, d}
    middle = [x for x in range(1, n+1) if x not in special]

    path1 = [a, c] + middle + [d, b]
    path2 = [c, a] + middle + [b, d]

    print(' '.join(map(str, path1)))
    print(' '.join(map(str, path2)))

main()