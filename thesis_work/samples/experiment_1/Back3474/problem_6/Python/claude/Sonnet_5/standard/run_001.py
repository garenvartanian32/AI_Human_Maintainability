from typing import List

class Solution:
    def maximumElegance(self, items: List[List[int]], k: int) -> int:
        items.sort(key=lambda x: -x[0])
        total = 0
        seen = set()
        stack = []
        for i in range(k):
            p, c = items[i]
            total += p
            if c in seen:
                stack.append(p)
            else:
                seen.add(c)
        best = total + len(seen)**2
        for i in range(k, len(items)):
            p, c = items[i]
            if c not in seen and stack:
                total += p - stack.pop()
                seen.add(c)
                best = max(best, total + len(seen)**2)
        return best