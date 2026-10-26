# copy
# https://beta.atcoder.jp/contests/arc097/submissions/2509168

N = int(input())
balls = []
for _ in range(2 * N):
    c, a = input().split()
    balls += [(c, int(a))]

# numB[i][b]: 初期状態において、i番目の玉より右にある、1～bが書かれた黒玉の数
numB = [[0] * (N + 1) for _ in range(2 * N)]
numW = [[0] * (N + 1) for _ in range(2 * N)]
for i in reversed(range(1, 2 * N)):
    c, a = balls[i]
    numB[i - 1] = list(numB[i])
    numW[i - 1] = list(numW[i])
    if c == 'B':
        for b in range(a, N + 1):
            numB[i - 1][b] += 1
    else:
        for w in range(a, N + 1):
            numW[i - 1][w] += 1

# costB[b][w]: 黒玉1～b、白玉1～wのうち、初期状態において、黒玉[b+1]より右にある玉の数
costB = [[0] * (N + 1) for _ in range(N + 1)]
costW = [[0] * (N + 1) for _ in range(N + 1)]
for i in reversed(range(2 * N)):
    c, a = balls[i]
    if c == 'B':
        cost = numB[i][a - 1]
        for w in range(N + 1):
            costB[a - 1][w] = cost + numW[i][w]
    else:
        cost = numW[i][a - 1]
        for b in range(N + 1):
            costW[b][a - 1] = cost + numB[i][b]

INF = 10 ** 9 + 7
# dp[b][w]: 黒玉1-b, 白玉1-wまで置いたときの転倒数の最小値
dp = [[INF] * (N + 1) for _ in range(N + 1)]
dp[0][0] = 0
for b in range(N + 1):
    for w in range(N + 1):
        if b > 0:
            dp[b][w] = min(dp[b][w], dp[b - 1][w] + costB[b - 1][w])
        if w > 0:
            dp[b][w] = min(dp[b][w], dp[b][w - 1] + costW[b][w - 1])

print(dp[N][N])