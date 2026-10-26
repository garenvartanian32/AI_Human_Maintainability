class Solution:
    def minAbsoluteDifference(self, nums: List[int], x: int) -> int:
        nums.sort()
        min_diff = float('inf')
        for i in range(len(nums)):
            if i + x < len(nums):
                min_diff = min(min_diff, abs(nums[i] - nums[i + x]))
        return min_diff