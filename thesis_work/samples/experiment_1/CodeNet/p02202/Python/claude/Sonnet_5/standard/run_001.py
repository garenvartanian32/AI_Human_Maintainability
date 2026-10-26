def main():
    n = int(input())
    v = list(map(int, input().split()))
    total = sum(v) - n*(n+1)//2
    print(total)

main()