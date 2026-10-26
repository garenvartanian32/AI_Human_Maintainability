class Solution:
    def maximumScore(self, nums: List[int], k: int) -> int:
        def prime_score(num):
            prime_factors = set()
            while num > 1:
                for i in range(2, num + 1):
                    if num % i == 0:
                        prime_factors.add(i)
                        num //= i
                        break
            return len(prime_factors)
        
        n = len(nums)
        dp = [[0] * n for _ in range(k + 1)]
        dp[0][0] = 1
        for i in range(1, k + 1):
            for j in range(1, n):
                for k in range(j):
                    if prime_score(nums[k]) > prime_score(nums[j]):
                        dp[i][j] = max(dp[i][j], dp[i - 1][k] * nums[j])
                    else:
                        dp[i][j] = max(dp[i][j], dp[i][k] * nums[j])
        return dp[k][n - 1] % (10 ** 9 + 7)