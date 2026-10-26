import sys

def main():
    data = sys.stdin.read().split()
    n = int(data[0])
    rows = data[1:1+n]
    # build bit matrix
    mat = []
    for r in rows:
        bits = []
        for ch in r:
            v = int(ch, 16)
            for shift in range(3, -1, -1):
                bits.append((v >> shift) & 1)
        mat.append(bits)
    # prefix sum
    pre = [[0]*(n+1) for _ in range(n+1)]
    for i in range(1, n+1):
        row_sum = 0
        for j in range(1, n+1):
            row_sum += mat[i-1][j-1]
            pre[i][j] = pre[i-1][j] + row_sum

    def block_sum(r1, c1, r2, c2):
        # inclusive, 1-indexed
        return pre[r2][c2] - pre[r1-1][c2] - pre[r2][c1-1] + pre[r1-1][c1-1]

    # find divisors of n descending
    divisors = [x for x in range(1, n+1) if n % x == 0]
    divisors.sort(reverse=True)
    for x in divisors:
        ok = True
        for bi in range(0, n, x):
            if not ok:
                break
            for bj in range(0, n, x):
                s = block_sum(bi+1, bj+1, bi+x, bj+x)
                if s != 0 and s != x*x:
                    ok = False
                    break
        if ok:
            print(x)
            return

main()