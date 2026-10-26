class Solution:
    def maxSum(self, nums: List[int]) -> int:
        max_digit = {}
        for num in nums:
            digit = max(int(d) for d in str(num))
            if digit not in max_digit:
                max_digit[digit] = []
            max_digit[digit].append(num)
        res = -1
        for digit, values in max_digit.items():
            if len(values) >= 2:
                values.sort(reverse=True)
                res = max(res, values[0] + values[1])
        return res