def main():
    n, m = map(int, input().split())
    stores = []
    for _ in range(n):
        a, b = map(int, input().split())
        stores.append((a,b))
    stores.sort()
    total = 0
    remaining = m
    for price, cap in stores:
        take = min(cap, remaining)
        total += take*price
        remaining -= take
        if remaining == 0:
            break
    print(total)

main()