
"""

普通なら転倒数を計算すればよい
でも今回は…？

最終形を確定すればその転倒数を求めればよくなる
何処に白でどこに黒が良いのかを考える…？

2000 C 2 = 2*(10**6) 通り調べるか…？
1回で2000log2000かかるので無理

転倒数をdpする…？
黒の数を保持してdp → 4000 * 4000 log 4000

戻す場合も転倒数は同じ！！→最重要

まずlistに各色・番の初期位置を記録
BWを決めた時にその位置の数字は確定
その並び替えから元に戻す場合の転倒数を計算！！

O(N^2)で解ける！！
dp[i][j] = 左詰めでBをi個,Ｗをj個置いた時の移動回数の最小値
何回動かす必要があるのか？？



"""

#bit解 O(N^2 * logN)なのできつい

def bitadd(a,w,bit): #aにwを加える(1-origin)
 
    x = a
    while x <= (len(bit)-1):
        bit[x] += w
        x += x & (-1 * x)
 
def bitsum(a,bit): #ind 1～aまでの和を求める
 
    ret = 0
    x = a
    while x > 0:
        ret += bit[x]
        x -= x & (-1 * x)
    return ret

"""
def cop(l):

    ret = []
    for i in l:
        ret.append(i)

    return ret


N = int(input())

Blis = [None] * (N+1)
Wlis = [None] * (N+1)

for i in range(N*2):

    c,a = input().split()
    a = int(a)

    if c == "B":
        Blis[a] = i+1
    else:
        Wlis[a] = i+1

#print (Blis)
#print (Wlis)

#各Bの数での最小の転倒数をBITと別リストで管理

BITs = [[0] * (2*N+1)]
revs = [float("inf")] * (N+1)
revs[0] = 0

#リストが大きすぎて毎回生成はあかん？→Bが大きい方から更新してけばおｋ
#必ず移行しなくてはいけない、具体的な転倒数のリストだけinfで初期化しておけばいい
#回が同じときのjでのBITの中味は固定→copyする必要なし…？

#BITにあらかじめBを置いておく
for i in range(N):

    new = cop(BITs[-1])
    bitadd (Blis[i+1] , 1 , new)
    BITs.append(new)


for i in range(2 * N):

    i += 1

    #BITの更新
    # ～i まで Wの処理をする

    for j in range(min(i , N + 1)):

        if i - j <= N:
            bitadd( Wlis[i-j] , 1 , BITs[j] )

    nrevs = [float("inf")] * (N+1)

    for j in range(min(i , N+1)):

        if i - j <= N:
            nrevs[j] = min(nrevs[j] , revs[j] + ( i - bitsum( Wlis[i-j] , BITs[j])))

        if j != N:
            nrevs[j+1] = min(nrevs[j+1] , revs[j] + (i - bitsum( Blis[j+1] , BITs[j+1] )))

    revs = nrevs
print (revs[-1])

"""

#準備N**2 & dp N**2 解
#WX に関して、まずそれより小さいWxが前にいくつあるかを計算→普通に転倒数
#その後、By(0~N)まで見てWXより前にあるものがいくつかをそれぞれ計算していく
#それをWB逆転に関しても行う→全体でO(N**2)

N = int(input())

Blis = [None] * (N+1)
Wlis = [None] * (N+1)

for i in range(2*N):

    i += 1

    c,a = input().split()
    a = int(a)

    if c == "B":
        Blis[a] = i
    else:
        Wlis[a] = i

#WX,BXそれぞれに関して転倒してない数計算？

B_bef_B = [0] #それ以前にあるそれより小さいBの数
BBIT = [0] * (2 * N + 1)

for i in range(N):
    i += 1

    B_bef_B.append (bitsum(Blis[i] , BBIT))
    bitadd (Blis[i] , 1 , BBIT)

W_bef_W = [0]
WBIT = [0] * (2*N+1)

for i in range(N):
    i += 1
    W_bef_W.append ( bitsum(Wlis[i],WBIT) )
    bitadd (Wlis[i] , 1 , WBIT)

#print (B_bef_B)
#print (W_bef_W)


#次に各BXに関して, WYまで印をつけた時にBXより前にいくつあるかを計算する
#O(N**2)

WY_bef_BX = [[0] * (N+1)] #[x][y]でその数を表示

for x in range(N):
    x += 1

    lis = [0]

    for y in range(N):
        y += 1
        if Wlis[y] < Blis[x]:
            lis.append(lis[-1] + 1)
        else:
            lis.append(lis[-1])

    WY_bef_BX.append(lis)


BY_bef_WX = [[0] * (N+1)]

for x in range(N):
    x += 1

    lis = [0]

    for y in range(N):
        y += 1
        if Blis[y] < Wlis[x]:
            lis.append(lis[-1] + 1)
        else:
            lis.append(lis[-1])

    BY_bef_WX.append(lis)

#print (WY_bef_BX)
#print (BY_bef_WX)

#具体的なDP
#dp[b][w] = b,w番目まで左に寄せた時のコストの最小値
#dp[b][w] = min(dp[b-1][w] + bを足した時のコスト , dp[b][w-1] + wを足した時のコスト)
# bを足した時のコスト = (Blis[b] - 1) - (B_bef_B[b] +  WY_bef_BX[b][w])
# w～　も同じように

dp = [ [float("inf")] * (N+1) for i in range(N+1) ]
dp[0][0] = 0

for i in range(2*N): #iは今現在置いてあるボールの数

    for b in range(i+1):

        w = i-b

        if b < N and w <= N:
            dp[b+1][w] = min(dp[b+1][w] , dp[b][w] + (Blis[b+1] - 1) - (B_bef_B[b+1] + WY_bef_BX[b+1][w]))

        if w < N and b <= N:
            #if b == 2 and w == 0:
                #print (dp[b][w] , (Wlis[w+1] - 1) , W_bef_W[w+1] , BY_bef_WX[b][w+1])
            
            dp[b][w+1] = min(dp[b][w+1] , dp[b][w] + (Wlis[w+1] - 1) - (W_bef_W[w+1] + BY_bef_WX[w+1][b]))

#print (dp)
print (dp[-1][-1])