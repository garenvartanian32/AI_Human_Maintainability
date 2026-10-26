class Solution:
    def minAbsoluteDifference(self, nums: List[int], x: int) -> int:
        nums.sort()
        n = len(nums)
        ans = float('inf')
        for i in range(n):
            for j in range(i+1, n):
                if abs(nums[i] - nums[j]) < ans:
                    ans = abs(nums[i] - nums[j])
                if abs(nums[i] - nums[j]) == ans and abs(nums[i] - x) < abs(nums[j] - x):
                    ans = abs(nums[i] - x)
        return ans