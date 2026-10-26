for _ in range(int(input())):
    n = int(input())
    A = list(map(int, input().split()))
    B = list(map(int, input().split()))
    dp = [0] * n
    for i in range(n):
        for j in range(i - 1, -1, -1):
            if A[i] == A[j]: continue
            s = abs(B[i] - B[j])
            dp[i], dp[j] = max(dp[i], dp[j] + s), max(dp[j], dp[i] + s)
    print(max(dp))