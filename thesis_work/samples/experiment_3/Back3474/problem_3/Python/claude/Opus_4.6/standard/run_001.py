from sortedcontainers import SortedList

class Solution:
    def minAbsoluteDifference(self, nums, x):
        n = len(nums)
        sl = SortedList()
        ans = float('inf')
        for j in range(x, n):
            sl.add(nums[j - x])
            cur = nums[j]
            idx = sl.bisect_left(cur)
            if idx < len(sl):
                ans = min(ans, sl[idx] - cur)
            if idx > 0:
                ans = min(ans, cur - sl[idx - 1])
        return ans