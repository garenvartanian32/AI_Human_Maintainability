class Solution:
    def maximumScore(self, nums: List[int], k: int) -> int:
        n = len(nums)
        dp = [0]*n
        dp[0] = nums[0]
        for i in range(1, n):
            dp[i] = max(dp[i-1], nums[i])
        ans = dp[k-1]
        for i in range(k-1, n):
            ans = max(ans, dp[i] * dp[k-1-i])
        return ans % (10**9 + 7)