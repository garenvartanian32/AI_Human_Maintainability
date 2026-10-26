import sys
import os.path
from collections import *
import math
import bisect

if (os.path.exists('input.txt')):
    sys.stdin = open("input.txt", "r")
    sys.stdout = open("output.txt", "w")
else:
    input = sys.stdin.readline

dp = [0] * (2 * 10**5 + 10)
for i in range(10):
    dp[i] = 1

N = 2 * 10**5 + 10
mod = 10**9 + 7

for i in range(10,N):
    dp[i] = (dp[i - 10] + dp[i - 9])%mod

tt = int(input())

while tt:
    tt -= 1
    n,m = [int(x) for x in input().split()]
    res = 0
    while n:
        x = n%10
        res = (res + dp[x + m])%mod
        n //= 10
    print(res)