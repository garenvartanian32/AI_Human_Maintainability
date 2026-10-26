N,K = map(int, input().split())

A = list(map(int, input().split()))

dp = [0] * (K+1)

for k in range(0,K+1):
    for i in A:
        if dp[k] == 0 and k-i >= 0 and dp[k-i] == 0:
            dp[k] = 1

            
if dp[k] == 1:
    print('First')
else:
    print('Second')