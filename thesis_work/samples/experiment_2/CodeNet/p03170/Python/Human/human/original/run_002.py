N,K=map(int, input().split())
A=list(map(int,input().split()))
dp=[0]*(K+1)
for i in range(K+1):
  for j in A:
    if (i>=j) and (dp[i-j]==0):
      dp[i]=1
if dp[K]:
  print('First')
else:
  print('Second')