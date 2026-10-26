# F - Silver Fox vs Monster

from math import ceil

n, d, a = map(int, input().split())
xh = [tuple(map(int, input().split())) for _ in range(n)]

# X座標の昇順に整列
xh.sort(key=lambda x: x[0])

bomb = 0
head = 0
accum = 0  # 破壊済みの体力
for i in range(n):
    while xh[head][0] < xh[i][0] - d - d:
        accum -= ceil(xh[head][1] / a) * a
        head += 1
    xh[i] = (xh[i][0], max(0, xh[i][1] - accum))
    b = ceil(xh[i][1] / a)
    bomb += b
    accum += b * a

print(bomb)
