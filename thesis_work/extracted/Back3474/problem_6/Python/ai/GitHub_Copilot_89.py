class Solution:
    def findMaximumElegance(self, items: List[List[int]], k: int) -> int:
        items.sort(key = lambda x: x[0], reverse = True)
        category = {}
        for i in range(len(items)):
            if items[i][1] not in category:
                category[items[i][1]] = 1
            else:
                category[items[i][1]] += 1
        category = sorted(category.items(), key = lambda x: x[1], reverse = True)
        ans = 0
        for i in range(k):
            ans += items[i][0]
        ans += len(category) ** 2
        return ans