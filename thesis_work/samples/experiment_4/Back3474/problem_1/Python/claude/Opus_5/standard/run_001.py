class Solution:
    def maxSum(self, nums):
        best = [-1] * 10
        ans = -1
        for x in nums:
            d = 0
            t = x
            while t:
                d = max(d, t % 10)
                t //= 10
            if best[d] != -1:
                ans = max(ans, best[d] + x)
            if x > best[d]:
                best[d] = x
        return ans