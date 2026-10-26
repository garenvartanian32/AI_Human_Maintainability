class Solution:    
    def maxSum (self, nums: List[int]) -> int:
        
        nums.sort()
        
        max_sum = 0
        
        for i in range(len(nums)-1):
            if nums[i] == nums[i+1]:
                max_sum = max(max_sum, nums[i]*(len(nums)-i-1))
                
        return max_sum