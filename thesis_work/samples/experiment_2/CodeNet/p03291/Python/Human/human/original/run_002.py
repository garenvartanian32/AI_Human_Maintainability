from sys import stdin

S = stdin.readline().rstrip()
inf = 10 ** 9 + 7

dp = [[0] * 4 for _ in range(len(S) + 1)]
dp[len(S)][3] = 1
for k in range(3):
    dp[len(S)][k] = 0

for k in range(len(S) - 1, -1, -1):
    if S[k] == '?':
        dp[k][3] = 3 * dp[k + 1][3]
    else:
        dp[k][3] = dp[k + 1][3]
    dp[k][3] %= inf

for i in range(len(S) - 1, -1, -1):
    for j in range(2, -1, -1):
        if S[i] == '?':
            dp[i][j] += 3 * dp[i + 1][j]
        else:
            dp[i][j] += dp[i + 1][j]
        s = 'ABC'
        if S[i] == '?' or S[i] == s[j]:
            dp[i][j] += dp[i + 1][j + 1]
        dp[i][j] %= inf

print(dp[0][0] % inf)