from bisect import bisect_left as bl
from math import pi
fx = 1
fy = 1
x1, y1, x2, y2 = map(int, input().split())
if x1 > x2: fx = -1
if y1 > y2: fy = -1
N = int(input())
inf = 10 ** 9
f = [list(map(int, input().split())) for _ in range(N)]
f = [[p[0] * fx, p[1] * fy] for p in f if (min(x1, x2) <= p[0] <= max(x1, x2) and min(y1, y2) <= p[1] <= max(y1, y2))]
f.sort(key = lambda x: (x[0], -x[1]))
lis = [inf] * N
lislen = 0
for i in range(len(f)):
  t = f[i][1]
  ind = bl(lis, t)
  lis[ind] = t
  lislen = max(lislen, ind + 1)
if lislen == min(abs(x2 - x1), abs(y2 - y1)) + 1:
  print((abs(x2 - x1) + abs(y2 - y1)) * 100 - 20 * lislen + 10 * pi + 5 * pi * max(lislen - 1, 0))
else:
  print((abs(x2 - x1) + abs(y2 - y1)) * 100 - 20 * lislen + 5 * pi * lislen)