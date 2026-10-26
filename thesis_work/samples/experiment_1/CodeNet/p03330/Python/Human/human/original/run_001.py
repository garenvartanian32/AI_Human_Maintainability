import sys
import numpy as np
import itertools

sr = lambda: sys.stdin.readline().rstrip()
ir = lambda: int(sr())
lr = lambda: list(map(int, sr().split()))

N, C = lr()
D = np.array([lr() for _ in range(C)])

grid = [lr() for _ in range(N)]
three = [[0] * C for _ in range(3)]
for i in range(N):
    for j in range(N):
        remain = (i+j) % 3
        three[remain][grid[i][j]-1] += 1

three = np.array(three)
answer = 10 ** 10
for x, y, z in itertools.permutations(range(C), 3):
    # three[0]をxの色にする
    result = 0
    for i in range(3):
        s = [x, y, z][i]
        result += (three[i] * D[:, s]).sum()
    if result < answer:
        answer = result

print(answer)
# 35