from typing import List
import bisect

class Solution:
    def minAbsoluteDifference(self, nums: List[int], x: int) -> int:
        n = len(nums)
        sl = []
        ans = float('inf')
        for i in range(x, n):
            bisect.insort(sl, nums[i-x])
            pos = bisect.bisect_left(sl, nums[i])
            if pos < len(sl):
                ans = min(ans, abs(sl[pos]-nums[i]))
            if pos > 0:
                ans = min(ans, abs(sl[pos-1]-nums[i]))
        return ans