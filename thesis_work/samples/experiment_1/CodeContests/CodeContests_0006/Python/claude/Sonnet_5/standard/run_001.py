def main():
    n = int(input())
    x = list(map(int, input().split()))
    v = list(map(int, input().split()))
    pts = sorted(zip(x, v))
    total = 0
    for i in range(n-1):
        xi, vi = pts[i]
        xj, vj = pts[i+1]
        if vi <= vj:
            total += xj - xi
    print(total)

main()