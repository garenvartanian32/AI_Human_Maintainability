MOD = 10 ** 9 + 7
_MAXV = 100001
_spf = list(range(_MAXV))
for i in range(2, int(_MAXV ** 0.5) + 1):
    if _spf[i] == i:
        for j in range(i * i, _MAXV, i):
            if _spf[j] == j:
                _spf[j] = i

def _prime_score(x):
    s = 0
    last = -1
    while x > 1:
        p = _spf[x]
        if p != last:
            s += 1
            last = p
        x //= p
    return s

class Solution:
    def maximumScore(self, nums, k):
        n = len(nums)
        scores = [_prime_score(x) for x in nums]
        left = [-1] * n
        stack = []
        for i in range(n):
            while stack and scores[stack[-1]] < scores[i]:
                stack.pop()
            left[i] = stack[-1] if stack else -1
            stack.append(i)
        right = [n] * n
        stack = []
        for i in range(n - 1, -1, -1):
            while stack and scores[stack[-1]] <= scores[i]:
                stack.pop()
            right[i] = stack[-1] if stack else n
            stack.append(i)
        order = sorted(range(n), key=lambda i: -nums[i])
        ans = 1
        remaining = k
        for i in order:
            if remaining <= 0:
                break
            cnt = (i - left[i]) * (right[i] - i)
            cnt = min(cnt, remaining)
            ans = ans * pow(nums[i], cnt, MOD) % MOD
            remaining -= cnt
        return ans