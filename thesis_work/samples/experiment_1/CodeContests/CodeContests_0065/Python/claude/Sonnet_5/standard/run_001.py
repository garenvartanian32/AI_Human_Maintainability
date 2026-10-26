def main():
    q = int(input())
    n = q
    factors = []
    d = 2
    while d*d <= n:
        while n % d == 0:
            factors.append(d)
            n //= d
        d += 1
    if n > 1:
        factors.append(n)
    K = len(factors)
    if K <= 1:
        print(1)
    elif K == 2:
        print(2)
    else:
        print(1)
        print(factors[0]*factors[1])

main()