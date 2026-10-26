import sys,math,itertools
from collections import Counter,deque,defaultdict
from bisect import bisect_left,bisect_right 
from heapq import heappop,heappush,heapify, nlargest
from copy import deepcopy
mod = 10**9+7
INF = float('inf')
def inp(): return int(sys.stdin.readline())
def inpl(): return list(map(int, sys.stdin.readline().split()))
def inpl_1(): return list(map(lambda x:int(x)-1, sys.stdin.readline().split()))
def inps(): return sys.stdin.readline()
def inpsl(x): tmp = sys.stdin.readline(); return list(tmp[:x])
def err(x): print(x); exit()

class BIT:
    def __init__(self, n):
        self.n = n
        self.data = [0]*(n+1)
        self.el = [0]*(n+1)
    def sum(self, i):
        s = 0
        while i > 0:
            s += self.data[i]
            i -= i & -i
        return s
    def add(self, i, x):
        # assert i > 0
        self.el[i] += x
        while i <= self.n:
            self.data[i] += x
            i += i & -i
    def get(self, i, j=None):
        if j is None:
            return self.el[i]
        return self.sum(j) - self.sum(i)

# 1_indexed
# n = 6
# a = [1,2,3,4,5,6]
# bit = BIT(n)
# for i,e in enumerate(a):
#     bit.add(i+1,e)
# print(bit.get(2,5)) #12 (3+4+5)

n = inp()
X = inpl()
V = inpl()
d = {}; dd={}
for i,x in enumerate(sorted(X)):
    d[x] = i; dd[i]=x
for i,x in enumerate(X):
    X[i] = d[x]
xv = [(x,v) for x,v in zip(X,V)]
xv.sort(key=lambda x:x[0] ,reverse=True)
xv.sort(key=lambda x:x[1], reverse=True)
cnt_bit = BIT(n+10)
sum_bit = BIT(n+10)
res = 0
for x,_ in xv:
    res += sum_bit.get(x,n+5) - cnt_bit.get(x,n+5)*dd[x]
    sum_bit.add(x+1,dd[x])
    cnt_bit.add(x+1,1)
print(res)
