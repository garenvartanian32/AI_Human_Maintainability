import sys
import math
import fractions
from collections import deque
from collections import defaultdict
sys.setrecursionlimit(10**7)

X, Y, A, B, C = map(int, input().split(' '))
p = sorted(list(map(int, input().split(' '))))
q = sorted(list(map(int, input().split(' '))))
r = sorted(list(map(int, input().split(' '))))

p = deque(p[-X:])
q = deque(q[-Y:])
r = deque(r)

while min(p[0], q[0]) < r[-1]:
    x = r.pop()
    if p[0] < q[0]:
        p.popleft()
        p.append(x)
    else:
        q.popleft()
        q.append(x)
    if len(r) == 0:
        break

cnt = 0
for x in p:
    cnt += x
for y in q:
    cnt += y

print(cnt)
