import itertools

n = int(input())
ha = [list(map(int, input().split())) for _ in range(n)]

h = []
a = []
for hai in ha:
    h.append(hai[0])
    a.append(hai[1])

cnt = 0

for i, j in itertools.product(h, a):
    if i == j:
        cnt += 1

print(cnt)