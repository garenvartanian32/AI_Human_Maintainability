import sys;input=sys.stdin.readline
# from bisect import bisect
# from collections import defaultdict
# from itertools import accumulate
# from decimal import *
# import math
# getcontext().prec = 50
# s = input().strip()
# n = int(input())
# lis = list(map(int,input().split()))
# x,y = map(int,input().split())
# chars = 'abcdefghijklmnopqrstuvwxyz'
# import heapq
# def gcd(a,b):
#     return gcd (b, a % b) if b else a

def solve():
    input()
    k,n,m = map(int,input().split())
    A = list(map(int,input().split()))
    B = list(map(int,input().split()))
    i,j = 0,0
    flag=1
    ans = []
    while i<n and j<m:
        if A[i]>k and B[j]>k:
            print(-1)
            return
        if flag:
            if A[i]==0:
                k+=1
                ans.append(A[i])
                i+=1
                continue
            else:
                if A[i]<=k:
                    ans.append(A[i])
                    i+=1
                    continue
                else:
                    flag = 0
        else:
            if B[j]==0:
                ans.append(B[j])
                k+=1
                j+=1
                continue
            else:
                if B[j]<=k:
                    ans.append(B[j])
                    j+=1
                    continue
                else:
                    flag = 1
    if i==n:
        while j<m:
            if B[j]==0:
                ans.append(B[j])
                k+=1
                j+=1
                continue
            else:
                if B[j]<=k:
                    ans.append(B[j])
                    j+=1
                    continue
                else:
                    print(-1)
                    return
    if j==m:
        while i<n:
            if A[i]==0:
                k+=1
                ans.append(A[i])
                i+=1
                continue
            else:
                if A[i]<=k:
                    ans.append(A[i])
                    i+=1
                    continue
                else:
                    print(-1)
                    return
    print(*ans)
        
for _ in range(int(input())):
    solve()    