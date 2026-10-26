class Solution:
    def findMaximumElegance(self, items: List[List[int]], k: int) -> int:
        n = len(items)
        max_elegance = 0
        
        # Generate all possible combinations of items with size k
        for combo in combinations(items, k):
            total_profit = sum(item[0] for item in combo)
            categories = [item[1] for item in combo]
            distinct_categories = len(Counter(categories))
            elegance = total_profit + distinct_categories ** 2
            max_elegance = max(max_elegance, elegance)
        
        return max_elegance