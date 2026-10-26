import sys
import math
import heapq
from operator import itemgetter
import bisect

sys.setrecursionlimit(10**7)
INTMAX = 9223372036854775807
INTMIN = -9223372036854775808
DVSR = 1000000007
def POW(x, y): return pow(x, y, DVSR)
def INV(x, m=DVSR): return pow(x, m - 2, m)
def DIV(x, y, m=DVSR): return (x * INV(y, m)) % m
def LI(): return [int(x) for x in sys.stdin.readline().split()]
def LF(): return [float(x) for x in sys.stdin.readline().split()]
def LS(): return sys.stdin.readline().split()
def II(): return int(sys.stdin.readline())
def FLIST(n):
    res = [1]
    for i in range(1, n+1): res.append(res[i-1]*i%DVSR)
    return res

class Bit:
    def __init__(self, n):
        self.size = n
        self.tree = [0] * (n + 1)

    def sum(self, i):
        i = i+1
        s = 0
        while i > 0:
            s += self.tree[i]
            i -= i & -i
        return s

    def add(self, i, x):
        i = i+1
        while i <= self.size:
            self.tree[i] += x
            i += i & -i

    def reset(self):
        t = self.tree
        for i in range(len(t)):
            t[i] = 0

class RBit:
    def __init__(self, n, B0 = None):
        self.BI0 = B0 or Bit(n+10)
        self.BI1 = Bit(n+10)

    def add(self, x, left, right):
        self.BI0.add(left, -x*(left-1))
        self.BI1.add(left, x)
        self.BI0.add(right+1, x*right)
        self.BI1.add(right+1, -x)

    def sum(self, i):
        return self.BI1.sum(i)*i + self.BI0.sum(i)

    def get(self, i):
        return self.sum(i) - (i and self.sum(i-1))

N,D,A=LI()
# RB = RBit(N+10)
PS=[]
HPS=[]
INI=Bit(N+10)
LIN = [LI() for i in range(N)]
LIN.sort(key = itemgetter(0))
for i in range(N):
    pos, hp = LIN[i]
    PS.append(pos)
    INI.add(i, hp)

RB=RBit(N+10, INI)

res = 0
for i in range(N):
    hp = RB.get(i)
    # print(hp)
    if hp > 0:
        atc_cnt = hp // A + (1 if hp%A else 0)
        left = PS[i]
        right = bisect.bisect_right(PS, PS[i]+2*D)-1
        RB.add(-A*atc_cnt, i, right)
        res += atc_cnt

print(res)
