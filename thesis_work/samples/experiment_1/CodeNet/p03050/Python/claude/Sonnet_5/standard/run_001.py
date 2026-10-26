def main():
    n = int(input())
    total = 0
    q = 1
    while q*q < n:
        if n % q == 0:
            m = n // q - 1
            if q < m:
                total += m
        q += 1
    print(total)

main()