

n, k = map(int, input().split())
arr = list(map(int, input().split()))

dp = [False] * (k + 1)

for i in range(1, k+1):
    for a in arr:
        if i - a >= 0 and not dp[i - a]:
            dp[i] = True

print("First" if dp[k] else "Second")
