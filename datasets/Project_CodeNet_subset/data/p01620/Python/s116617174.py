alpha = list("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ")

while 1:
    n = int(input())
    if n == 0:
        break
    K = list(map(int, input().split()))
    S = list(input())

    ans = ""
    for i, s in enumerate(S):
        diff = K[i % len(K)]
        if diff <= 0:
            num = 52 + diff
        else:
            num = diff
        ans += alpha[alpha.index(s)-num]

    print(ans)

