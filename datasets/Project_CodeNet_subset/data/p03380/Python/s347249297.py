n = int(input())
A = list(map(int, input().split()))


# xCy = xCx-y
# あるaについて，a/2に近い方が良い
# -> |aj - a / 2|となるajを選ぶ (a以外)

maxA = max(A)
mv = float("inf")
aj = None
for a in A:
    if a != maxA:
        value = abs(a - maxA // 2)
        if value < mv:
            mv = value
            aj = a

print(maxA, aj)