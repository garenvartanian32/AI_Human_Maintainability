S = input()
N = len(S)
K = int(input())

dp = [[[0 for _ in range(5)] for _ in range(2)] for _ in range(N+5)]
dp[1][0][1] = 1
dp[1][1][0] = 1
dp[1][1][1] = int(S[0])-1

for i in range(1, N):
    n = int(S[i])
    ok = 0 if n == 0 else 1
    for j in range(4):
        dp[i+1][0][j+ok] += dp[i][0][j]
        if n > 0:
            dp[i+1][1][j] += dp[i][0][j]
            dp[i+1][1][j+1] += (n-1)*dp[i][0][j]
        dp[i+1][1][j] += dp[i][1][j]
        dp[i+1][1][j+1] += 9*dp[i][1][j]

print(dp[N][0][K]+dp[N][1][K])