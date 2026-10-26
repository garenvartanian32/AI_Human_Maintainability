mod = 998244353
S, K = input().split()
a = [len(x) for x in S.split("0")]
K = min(int(K), sum(a)+1)

# f[i][j][k] : 右からi個目の区間まで見て、これまでにj個の1をpick upして、そのうちk個の置き場所が確定しているような操作を行った後の"状態数"
# ans -> f[len(a)][0][0] + f[len(a)][1][1] + ... + f[len(a)][K][K]


def solve_naive():  # TLE解
    f = [[[0 for k in range(K+1)] for j in range(K+1)]
         for i in range(len(a)+1)]

    for i in range(len(a)+1):
        if i == 0:
            # 区間を一つも見ていないので、1を選ぶことができない
            f[i][0][0] = 1
        else:
            for j in range(K+1):
                for k in range(j+1):
                    # 1. 右からi番目の区間a[-i]では何もしない（i.e. a[-i]から1を取らないし、持ってる1をa[-i]に置かない）
                    f[i][j][k] += f[i-1][j][k]
                    f[i][j][k] %= mod

                    # 2. a[-i]に1をいくつか置く
                    for num in range(1, j-k+1):
                        if k + num > K:
                            break
                        f[i][j][k+num] += f[i-1][j][k]  # a[-i]に1をnum個置く
                        f[i][j][k+num] %= mod

                    # 3. a[-i]から1をいくつか取る。
                    # 注: a[-1]に1を「置く」と「取る」を両方することは考えなくてよい。より少ない回数の操作で同じ状態が実現されるので。
                    for num in range(1, a[-i]+1):
                        if j + num > K:
                            break
                        f[i][j+num][k] += f[i-1][j][k]  # a[-i]から1をnum個取る
                        f[i][j+num][k] %= mod
    ans = 0
    for num in range(K+1):
        ans += f[len(a)][num][num]
        ans %= mod
    return ans


def solve():
    f = [[[0 for k in range(K+1)] for j in range(K+1)]
         for i in range(len(a)+1)]

    for i in range(len(a)+1):
        if i == 0:
            # 区間を一つも見ていないので、1を選ぶことができない
            f[i][0][0] = 1
        else:
            for j in range(K+1):
                cum2 = 0
                for k in range(j+1):
                    # 1. 右からi番目の区間a[-i]では何もしない（i.e. a[-i]から1を取らないし、持ってる1をa[-i]に置かない）
                    f[i][j][k] += f[i-1][j][k]
                    f[i][j][k] %= mod

                    # 2. a[-i]に1をいくつか置く
                    # f[i][j][k] += f[i-1][j][0] (a[-i]にk個置く) + f[i-1][j][1]+ ... + f[i-1][j][k-1] (a[-i]に1個置く)
                    f[i][j][k] += cum2
                    f[i][j][k] %= mod
                    cum2 += f[i-1][j][k]

            for k in range(K+1):
                cum3 = 0
                for j in range(k, K+1):
                    # 3. a[-i]から1をいくつか取る。
                    # 注: a[-1]に1を「置く」と「取る」を両方することは考えなくてよい。より少ない回数の操作で同じ状態が実現されるので。
                    # f[i][j][k] += f[i-1][k][k] (a[-i]からj-k個取る) + f[i-1][k+1][k] + .... + f[i-1][j-1][k] (a[-i]から1個取る)
                    if j < a[-i]:
                        f[i][j][k] += cum3
                        f[i][j][k] %= mod
                        cum3 += f[i-1][j][k]
                    else:
                        f[i][j][k] += cum3
                        f[i][j][k] %= mod
                        cum3 += f[i-1][j][k] - f[i-1][j-a[-i]][k]
    ans = 0
    for num in range(K+1):
        ans += f[len(a)][num][num]
        ans %= mod
    return ans


print(solve_naive())
