class Solution:
    def findMaximumElegance(self, items: List[List[int]], k: int) -> int:
        n = len(items)
        items.sort(key=lambda x: (-x[0], x[1]))
        dp = [[0, 0] for _ in range(n + 1)]
        for i in range(1, n + 1):
            dp[i][0] = max(dp[i - 1][0], dp[i - 1][1])
            dp[i][1] = max(dp[i - 1][0] + items[i - 1][0], dp[i - 1][1] + items[i - 1][0])
        return max(dp[k]) + len(set([item[1] for item in items[:k]])) ** 2