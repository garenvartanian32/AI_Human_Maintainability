class Solution:
    def minAbsoluteDifference(self, nums: List[int], x: int) -> int:
        nums.sort()
        n = len(nums)
        res = float('inf')
        for i in range(n):
            j = bisect_left(nums, nums[i] - x, i + 1)
            if j < n:
                res = min(res, abs(nums[i] - nums[j] + x))
            if j > i + 1:
                res = min(res, abs(nums[i] - nums[j - 1] + x))
        return res