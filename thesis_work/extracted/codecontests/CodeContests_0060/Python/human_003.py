from collections import defaultdict as dd, deque as dq
import math, string

import sys

def getInts():
    return [int(s) for s in input().split()]

def getInt():
    return int(input())

def getStrs():
    return [s for s in input().split()]

def getStr():
    return input()

def listStr():
    return list(input())

MOD = 998244353

"""
For all pairs i and j, we want to find f(Ai,Aj) and sum all the f
Note that they can be in either order

In this easy version, f(Ai,Aj) + f(Aj,Ai)
Each digit of Ai
a1, a2, ..., an

12 3 45

123
132

453
435

1425
4152

1122
1122

33
33

4455
4455

3500
3544

So we need to know how many numbers of each length there are, length can go from 1 to 10

"""

tens = [1]
curr = 1
for i in range(1,10**5+5):
    curr *= 10
    curr %= MOD
    tens.append(curr)

def solve():
    N = getInt()
    A = getInts()
    lens = [0]*11
    for a in A:
        lens[len(str(a))] += 1
#    print(lens)
    ans = 0
    for a in A:
        X = [int(s) for s in str(a)]
        for i, x in enumerate(X):
            for k in range(1,11):
                if not lens[k]:
                    continue
                L = lens[k]
#                print(i,x,k,X,len(X)-i)
                if len(X)-i > k:
#                    print(int(x)*tens[k+len(X)-i-1],L,k)
                    ans += int(x)*tens[k+len(X)-i-1]*L*2
                    ans %= MOD
                else:
#                    print(int(x)*tens[2*len(X)-i-1],L,k)
                    ans += int(x)*tens[2*(len(X)-i-1)]*L*11
                    ans %= MOD
#                print(ans)
    return ans
    
    
#for _ in range(getInt()):
print(solve())