N=int(input())
act=[]
for i in range(N):
    act.append(list(map(int,input().split())))
dp=[[0]*3 for i in range(N+1)]

for i in range(1,N+1):
    for j in range(3):
        for k in range(3):
            if(j!=k):
                dp[i][j]=max(dp[i][j],dp[i-1][k]+act[i-1][j])
                
print(max([dp[N][0],dp[N][1],dp[N][2]]))