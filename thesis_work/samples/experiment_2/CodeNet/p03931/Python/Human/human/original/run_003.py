M=10**9+7
o=lambda:map(int,raw_input().split())
n,k=o()
a=o()
dp=[[0]*256 for _ in [0]*(n+1)]
dp[0][0]=1
for i in range(n):
    _dp=dp
    for l in reversed(range(1,i+2)):
        for j in range(256):_dp[l][j]+=dp[l-1][a[i]^j]
    dp=_dp
p=[1]*(n+1)
for i in range(n):p[i+1]=p[i]*(i+1)%M
print sum([dp[l][k]*p[l] for l in range(1,n+1)])%M