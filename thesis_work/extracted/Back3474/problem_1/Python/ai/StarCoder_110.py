class Solution:
    def maxSum(self, nums: List[int]) -> int:
        if len(nums) == 1:
            return nums[0]
        if len(nums) == 2:
            return max(nums)
        max_sum = 0
        for i in range(len(nums)):
            for j in range(i+1, len(nums)):
                if nums[i] % 10 == nums[j] % 10:
                    max_sum = max(max_sum, nums[i] + nums[j])
        return max_sum if max_sum > 0 else -1