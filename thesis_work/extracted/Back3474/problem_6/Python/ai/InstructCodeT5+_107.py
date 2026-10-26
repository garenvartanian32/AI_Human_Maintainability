class Solution:    
    def findMaximumElegance(self, items: List[List[int]], k: int) -> int:
        
        @lru_cache(None)
        def dp(i, k):
            if k == 0:
                return 0
            if i == len(items):
                return float('-inf')
            return max(dp(i + 1, k), items[i][0] + dp(i + 1, k - 1))
        
        return dp(0, k)