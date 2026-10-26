class Solution:
    def maximumScore(self, nums: List[int], k: int) -> int:
        def prime(n):
            if n == 1:
                return False
            for i in range(2, int(n**0.5)+1):
                if n % i == 0:
                    return False
            return True
        ans = 1
        for i in range(k, -1, -1):
            if prime(nums[i]):
                ans *= nums[i]
        for i in range(k+1, len(nums)):
            if prime(nums[i]):
                ans *= nums[i]
        return ans % (10**9 + 7)