class Solution:    
    def maximumScore(self, nums: List[int], k: int) -> int:
        
        n = len(nums)
        
        @lru_cache(None)
        def dp(l, r, k):
            if k == 0:
                return 0
            if l > r:
                return 0
            if l == r:
                return nums[l]
            
            res = 0
            for i in range(l, r + 1):
                if i == l:
                    res = max(res, dp(l + 1, r, k - 1))
                elif i == r:
                    res = max(res, dp(l, r - 1, k - 1))
                else:
                    res = max(res, dp(l + 1, r, k - 1) + nums[i] * nums[i])
                    res = max(res, dp(l, r - 1, k - 1) + nums[i] * nums[i])
            return res % (10**9 + 7)
        
        return dp(0, n - 1, k)