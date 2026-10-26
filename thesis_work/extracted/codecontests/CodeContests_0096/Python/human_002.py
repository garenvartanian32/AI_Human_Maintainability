N = input()
K = int(input())
dp = [[[0 for k in range(K+2)] for j in range(2)] for i in range(len(N)+1)]
dp[0][0][0] = 1
for i in range(len(N)):
    Ni = int(N[i])
    for j in range(2):
        for k in range(K+1):
            for d in range(10 if j else int(Ni)+1):
                dp[i+1][j or (d < Ni)][k + (d != 0)] += dp[i][j][k]
                # print(i, j, k, d)

ans = dp[-1][0][K] + dp[-1][1][K]
# print(dp)
print(ans)