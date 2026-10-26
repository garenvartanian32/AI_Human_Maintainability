from operator import itemgetter
import itertools
def inpl(): return list(map(int, input().split()))
def cost(x, y):
    if x == y:
        return float('inf')
    return D * abs(x - y) + A[x] + A[y]
N, D = inpl()
A = inpl()
Right = [(v - i*D, i) for i, v in enumerate(A)]
Left = [(v + i*D, i) for i, v in enumerate(A)][::-1]
Right = list(map(itemgetter(1), itertools.accumulate(Right, min)))
Left = list(map(itemgetter(1), itertools.accumulate(Left, min)))[::-1]
ans = 0
hub = []
for i, (l, r) in enumerate(zip(Left, Right)) :
    if i == l == r:
        hub.append(i)
    else:
        ans += min(cost(i, r), cost(i, l))
print(ans + sum([cost(i, j) for i, j in zip(hub, hub[1:])]))
