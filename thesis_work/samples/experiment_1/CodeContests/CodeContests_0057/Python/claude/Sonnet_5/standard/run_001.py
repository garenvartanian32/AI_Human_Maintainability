import sys

def main():
    data = sys.stdin.buffer.read().split()
    n = int(data[0])
    rows = [data[i+1].decode() for i in range(n)]
    bits = []
    for row in rows:
        line = []
        for ch in row:
            v = int(ch, 16)
            line.append(format(v, '04b'))
        bits.append(''.join(line))
    grid = [[int(c) for c in row] for row in bits]
    divisors = [x for x in range(1, n+1) if n % x == 0]
    divisors.sort(reverse=True)
    for x in divisors:
        ok = True
        for bi in range(0, n, x):
            for bj in range(0, n, x):
                val = grid[bi][bj]
                good = True
                for i in range(bi, bi+x):
                    for j in range(bj, bj+x):
                        if grid[i][j] != val:
                            good = False
                            break
                    if not good:
                        break
                if not good:
                    ok = False
                    break
            if not ok:
                break
        if ok:
            print(x)
            return
    print(1)

main()