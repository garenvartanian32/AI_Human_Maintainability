import sys
input = sys.stdin.readline

N=int(input())
V=sum(map(int,input().split()))
print(V-N*(N+1)//2)

