class Solution:
    def findMaximumElegance(self, items: List[List[int]], k: int) -> int:
        items.sort(key = lambda x: x[1])
        ans = 0
        for i in range(k):
            ans += items[i][0]
        return ans + (k * (k - 1) // 2) * items[k - 1][1]