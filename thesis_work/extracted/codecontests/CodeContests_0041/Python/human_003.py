n,m=map(int,input().split())
a=set(int(input()) for i in range(m))
mod=10**9+7
dp=[0]*(n+1)
dp[0]=1
if 1 not in a:
    dp[1]=1

for i in range(2,n+1):
    if i not in a:
        dp[i]+=(dp[i-2]+dp[i-1])%mod
print(dp[-1])

    