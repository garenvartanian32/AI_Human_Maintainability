class Solution:
    def findMaximumElegance(self, items: List[List[int]], k: int) -> int:
        items = sorted(items, key=lambda v: -v[0])
        res = cur = 0
        A = []
        seen = set()
        for i, (p, c) in enumerate(items):
            if i < k:
                if c in seen:
                    A.append(p)
                cur += p
            elif c not in seen:
                if not A: break
                cur += p - A.pop()
            seen.add(c)
            res = max(res, cur + len(seen) * len(seen))
        return res