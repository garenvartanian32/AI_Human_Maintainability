class Solution:
    def findMaximumElegance(self, items, k):
        items = sorted(items, key=lambda it: -it[0])
        n = len(items)
        total = sum(items[i][0] for i in range(k))
        seen = set()
        dup = []
        for i in range(k):
            profit, cat = items[i]
            if cat in seen:
                dup.append(profit)
            else:
                seen.add(cat)
        ans = total + len(seen) ** 2
        for i in range(k, n):
            if not dup:
                break
            profit, cat = items[i]
            if cat in seen:
                continue
            total += profit - dup.pop()
            seen.add(cat)
            ans = max(ans, total + len(seen) ** 2)
        return ans