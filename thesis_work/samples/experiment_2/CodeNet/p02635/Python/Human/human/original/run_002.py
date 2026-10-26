s,k = input().split()
K = int(k)
if s[-1] == "1":
  s += "0"
n = len(s)
ls = []
cnt = 0
for i in range(n):
  if s[i] == "0":
    ls.append(cnt)
    cnt = 0
  else:
    cnt += 1
l = len(ls)
sm = sum(ls)
mod = 998244353
dp = [[[0 for i in range(sm+1)] for j in range(sm+1)] for k in range(l+1)]
dp[0][0][0] = 1
smprv = 0
for i in range(1,l+1):
  num = ls[i-1]
  for j in range(smprv+num,sm+1):
    if j == smprv+num:
      dp[i][j][0] = 1
    for k in range(1,j+1):
      x = 0
      for m in range(j+1):
        if num >= m:
          x += dp[i-1][j-m][k]
        else:
          x += dp[i-1][j-m][k-(m-num)]
      dp[i][j][k] = x%mod
  smprv += num
ans = 0
for i in range(sm+1):
  if i <= K:
    ans += dp[-1][-1][i]
    ans %= mod
print(ans)