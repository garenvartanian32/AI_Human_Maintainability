import numpy as np
from collections import deque
from collections import defaultdict
import heapq
import collections
import itertools
import bisect


def zz():
    return list(map(int, input().split()))


def z():
    return int(input())


def S():
    return input()


X, Y, A, B, C = zz()
p = zz()
q = zz()
r = zz()

p = sorted(p)
q = sorted(q)
r = sorted(r, reverse=True)
# 透明なしのとき
p = p[-X:]
q = q[-Y:]

heapq.heapify(p)
heapq.heapify(q)
min_p = heapq.heappop(p)
min_q = heapq.heappop(q)
for i in range(C):

    if (min_p <= min_q):
        if (min_p < r[i]):
            # pと入れ替える
            heapq.heappush(p, r[i])
            min_p = heapq.heappop(p)
        else:
            break
    else:
        if (min_q < r[i]):
            heapq.heappush(q, r[i])
            min_q = heapq.heappop(q)
        else:
            break


print(sum(p) + sum(q) + min_p + min_q)
