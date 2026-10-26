class Solution:
    def minAbsoluteDifference(self, nums: List[int], x: int) -> int:
        nums.sort()  # Sort the input array
        
        min_diff = float('inf')  # Initialize the minimum difference to positive infinity
        
        left = 0  # Initialize the left pointer
        right = x  # Initialize the right pointer
        
        while right < len(nums):
            min_diff = min(min_diff, nums[right] - nums[left])  # Update the minimum difference
            
            left += 1  # Move the left pointer
            right += 1  # Move the right pointer
        
        return min_diff