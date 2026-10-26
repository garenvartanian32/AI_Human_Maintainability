import numpy as np

MOD=998244353
N=int(input())
A=np.array([int(input()) for _ in range(N)],dtype=np.int64)

TOTAL=pow(3,N,MOD)
ASum=np.sum(A)

# dp=[[0 for _ in range(ASum+1)] for _ in range(2)]
dp=np.zeros((2,ASum+1),dtype=np.int64)
dp[0][0] = 2
dp[0][A[0]] = 1

# dpOther=[[0 for _ in range(ASum+1)] for _ in range(2)]
dpOther=np.zeros((2,ASum+1),dtype=np.int64)
dpOther[0][0] = 1
dpOther[0][A[0]] = 1

prev=0
current=1
curMax=A[0]
# for n in range(1,N):
for n, a in enumerate(A):
	if n==0:
		continue
	# curMax += a
	current = n % 2
	prev = (n+1) % 2
	# for e in range(curMax+1):
	dp[current,:] = dp[prev,:]*2 % MOD
	dp[current,a:] += dp[prev,:-a] % MOD

	if ASum %2 == 0:
		dpOther[current,:] = dpOther[prev,:] % MOD
		dpOther[current,a:] += dpOther[prev,:-a] % MOD

sumStart = 0
plus = 0

if ASum % 2 == 0:
	# 合計が偶数
	sumStart=ASum//2
	plus = (dpOther[current][ASum//2]*3) % MOD
else:
	# 合計が奇数の場合、ceil
	sumStart=ASum//2+1

exceptCnt=0
for s in range(sumStart,ASum+1):
	exceptCnt = (exceptCnt + dp[current][s]*3) % MOD

exceptCnt -= plus

print((TOTAL - exceptCnt) % MOD )

