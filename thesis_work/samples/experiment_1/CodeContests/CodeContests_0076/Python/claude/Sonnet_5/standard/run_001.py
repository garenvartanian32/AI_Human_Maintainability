def main():
    n = int(input())
    w = list(map(int, input().split()))
    c1 = sum(1 for x in w if x == 100)
    c2 = sum(1 for x in w if x == 200)
    if c1 % 2 != 0:
        print("NO")
        return
    if c1 == 0 and c2 % 2 != 0:
        print("NO")
        return
    print("YES")

main()