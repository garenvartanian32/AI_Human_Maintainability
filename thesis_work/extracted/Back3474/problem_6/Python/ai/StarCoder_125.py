class Solution:
    def findMaximumElegance(self, items: List[List[int]], k: int) -> int:
        n = len(items)
        dp = [[0]*n for _ in range(n)]
        for i in range(n):
            dp[i][i] = items[i][0]
        for l in range(2, n+1):
            for i in range(n-l+1):
                j = i+l-1
                dp[i][j] = max(dp[i][j-1], dp[i+1][j]) + items[j][0]
        return max(dp[0][k-1], dp[n-k][n-1])