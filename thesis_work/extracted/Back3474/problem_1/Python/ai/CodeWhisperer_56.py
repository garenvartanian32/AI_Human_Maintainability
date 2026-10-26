class Solution:
    def maxSum(self, nums: List[int]) -> int:
        nums.sort(reverse=True)
        for i in range(len(nums)-1):
            if nums[i]%10==nums[i+1]%10:
                return nums[i]+nums[i+1]
        return -1