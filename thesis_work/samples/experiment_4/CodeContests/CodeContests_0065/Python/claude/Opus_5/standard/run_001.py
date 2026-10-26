import sys

def main():
    q = int(sys.stdin.read().split()[0])
    if q == 1:
        print(1)
        print(0)
        return
    factors = []
    n = q
    d = 2
    while d * d <= n:
        while n % d == 0:
            factors.append(d)
            n //= d
        d += 1
    if n > 1:
        factors.append(n)
    k = len(factors)
    if k == 1:
        print(1)
        print(0)
    elif k == 2:
        print(2)
    else:
        factors.sort()
        print(1)
        print(factors[0] * factors[1])

main()