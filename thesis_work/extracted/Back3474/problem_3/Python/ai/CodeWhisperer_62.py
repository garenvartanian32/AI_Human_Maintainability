class Solution:
    def minAbsoluteDifference(self, nums: List[int], x: int) -> int:
        nums.sort()
        ans = float('inf')
        for i in range(len(nums)):
            j = bisect_left(nums, nums[i] + x)
            if j < len(nums):
                ans = min(ans, nums[j] - nums[i])
            if i + 1 < len(nums):
                ans = min(ans, nums[i + 1] - nums[i])
        return ans