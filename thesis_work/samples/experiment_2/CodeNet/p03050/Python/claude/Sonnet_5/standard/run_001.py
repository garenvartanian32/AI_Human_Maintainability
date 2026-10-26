import sys

def main():
    n = int(sys.stdin.read().strip())
    total = 0
    d = 1
    while d * d <= n:
        if n % d == 0:
            divisors = {d, n // d}
            for dd in divisors:
                m = dd - 1
                if m >= 1:
                    q = n // dd
                    if q < m:
                        total += m
        d += 1
    print(total)

main()