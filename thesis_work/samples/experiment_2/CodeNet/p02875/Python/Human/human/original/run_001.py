
"""
Writer: SPD_9X2
https://atcoder.jp/contests/agc040/tasks/agc040_c

奇数文字目と偶数文字目がセットで消える
奇数文字目のABを反転すると、AB or BA or AC or BC or CC で消えていいことになる

すなわち、異なる文字の切れ目では必ず消せる
異なる切れ目は必ず存在するので、Cを適当にABに割り振った時に全て消せるかどうかが問題になる

A,Bのみの時に全て消せる条件は 両者の数が等しい事
Cを適切に割り振った時に両者の数を等しくできる必要十分条件は max(a,b) <= N//2
あとはこれを数えればよい→どうやって？

全ての並び方は 3**N　ここから補集合を引く？
Aが半数を超える(k個)の時のおき方は、 NCk * (2**(N-k))で求まる
Bの時も同様に。

"""

def modfac(n, MOD):
 
    f = 1
    factorials = [1]
    for m in range(1, n + 1):
        f *= m
        f %= MOD
        factorials.append(f)
    inv = pow(f, MOD - 2, MOD)
    invs = [1] * (n + 1)
    invs[n] = inv
    for m in range(n, 1, -1):
        inv *= m
        inv %= MOD
        invs[m - 1] = inv
    return factorials, invs


def modnCr(n,r,mod,fac,inv):
    return fac[n] * inv[n-r] * inv[r] % mod


N = int(input())
mod = 998244353

fac,inv = modfac(N+10,mod)
ans = pow(3,N,mod)

tpow = [1]
for i in range(N//2+10):
    tpow.append(tpow[-1]*2%mod)

for k in range(N//2+1,N+1):

    now = 2 * modnCr(N,k,mod,fac,inv) * tpow[N-k]
    ans -= now
    ans %= mod

print (ans)
