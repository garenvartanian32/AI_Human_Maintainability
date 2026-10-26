class Solution:
    def minAbsoluteDifference(self, nums: List[int], x: int) -> int:
        nums.sort()
        res = float('inf')
        for i in range(len(nums)-1):
            res = min(res, abs(nums[i]-nums[i+1]))
        return res