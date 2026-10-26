class Solution:
    def maxSum(self, nums: List[int]) -> int:
        max_digit_sum = {}  # Dictionary to store the maximum sum for each maximum digit
        
        for num in nums:
            max_digit = max(str(num))  # Find the maximum digit in the current number
            if max_digit in max_digit_sum:
                max_digit_sum[max_digit] = max(max_digit_sum[max_digit], num)
            else:
                max_digit_sum[max_digit] = num
        
        max_sum = -1  # Initialize the maximum sum to -1
        
        # Iterate through the dictionary to find the maximum sum
        for digit, num in max_digit_sum.items():
            if max_digit_sum[digit] != num:
                max_sum = max(max_sum, num + max_digit_sum[digit])
        
        return max_sum