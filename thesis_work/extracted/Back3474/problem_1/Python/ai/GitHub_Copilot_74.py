class Solution:
    def maxSum(self, nums: List[int]) -> int:
        maxSum = -1
        maxDigit = [0] * 10
        for num in nums:
            maxDigit[int(str(num)[0])] = max(maxDigit[int(str(num)[0])], num)
        for i in range(10):
            
            for j in range(i, 10):
                if maxDigit[i] and maxDigit[j]:
                    maxSum = max(maxSum, maxDigit[i] + maxDigit[j])
        return maxSum