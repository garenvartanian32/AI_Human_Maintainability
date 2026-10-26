from typing import List

class Solution:
    def maximumScore(self, nums: List[int], k: int) -> int:
        MOD = 10**9+7
        n = len(nums)
        def prime_score(x):
            cnt = 0
            d = 2
            while d*d <= x:
                if x % d == 0:
                    cnt += 1
                    while x % d == 0:
                        x //= d
                d += 1
            if x > 1:
                cnt += 1
            return cnt
        ps = [prime_score(v) for v in nums]
        left = [-1]*n
        right = [n]*n
        stack = []
        for i in range(n):
            while stack and ps[stack[-1]] < ps[i]:
                right[stack[-1]] = i
                stack.pop()
            stack.append(i)
        stack = []
        for i in range(n-1, -1, -1):
            while stack and ps[stack[-1]] <= ps[i]:
                left[stack[-1]] = i
                stack.pop()
            stack.append(i)
        order = sorted(range(n), key=lambda i: -nums[i])
        ans = 1
        remaining = k
        for i in order:
            if remaining <= 0:
                break
            cnt = (i-left[i]) * (right[i]-i)
            use = min(cnt, remaining)
            ans = ans * pow(nums[i], use, MOD) % MOD
            remaining -= use
        return ans