def main():
    n = int(input())
    a = list(map(int, input().split()))
    maxa = max(a)
    for k in range(maxa, 200+1):
        elo = sum(min(x, k) for x in a)
        awruk = n*k - elo
        if awruk > elo:
            print(k)
            return
    print(maxa+1)

main()