class Solution:
    def findMaximumElegance(self, items: List[List[int]], k: int) -> int:
        n = len(items)
        profit = [0] * n
        category = [0] * n
        for i in range(n):
            profit[i] = items[i][0]
            category[i] = items[i][1]
        profit.sort(reverse=True)
        category.sort(reverse=True)
        profit = profit[:k]
        category = category[:k]
        
        profit_set = set(profit)
        category_set = set(category)
        
        profit_sum = sum(profit)
        category_sum = sum(category)
        
        return profit_sum + category_sum ** 2