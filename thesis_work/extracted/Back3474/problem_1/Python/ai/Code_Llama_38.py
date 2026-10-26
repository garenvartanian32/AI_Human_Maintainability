class Solution:
    def maxSum(self, nums: List[int]) -> int:
        for i in range(len(nums)):
            for j in range(i+1, len(nums)):
                if max(nums[i], nums[j]) % 10 == max(nums[i] // 10, nums[j] // 10):
                    return max(nums[i], nums[j])
        return -1