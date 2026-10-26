#!usr/bin/env python3
from itertools import accumulate
import sys
def LI(): return [int(x) for x in sys.stdin.readline().split()]

def solve():
    n,q = LI()
    a = LI()
    X = LI()
    for x in X:
        ans = 0
        l = 0
        s = 0
        for r in range(n+1):
            while s > x:
                s -= a[l]
                l += 1
            ans += r-l
            if r < n:
                s += a[r]
        print(ans)
    return

#Solve
if __name__ == "__main__":
    solve()

