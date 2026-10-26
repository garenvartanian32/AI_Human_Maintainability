class Solution:
    def maximumScore(self, nums: List[int], k: int) -> int:
        n = len(nums)
        MOD = 10**9 + 7

        # Precompute the prime score of all numbers in nums
        prime_score = [0] * n
        for i, num in enumerate(nums):
            for j in range(2, int(sqrt(num)) + 1):
                if num % j == 0:
                    prime_score[i] += 1
                    while num % j == 0:
                        num //= j
            if num > 1:
                prime_score[i] += 1

        # Compute the maximum score using dynamic programming
        dp = [1]
        for _ in range(k):
            new_dp = [0] * (n + 1)
            for l in range(n):
                for r in range(l, n):
                    max_prime_score = max(prime_score[l:r+1])
                    max_index = prime_score[l:r+1].index(max_prime_score) + l
                    new_dp[r+1] = max(new_dp[r+1], dp[l] * nums[max_index] % MOD)
            dp = new_dp

        return max(dp) % MOD