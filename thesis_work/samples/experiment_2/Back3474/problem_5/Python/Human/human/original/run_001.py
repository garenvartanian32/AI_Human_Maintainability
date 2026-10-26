class Solution: 
    MOD = 1000000007

    def maximumScore(self, nums: List[int], k: int) -> int:
        n = len(nums)

        upper = max(nums) + 1

        prime = [True]*upper
        prime[0] = prime[1] = False
        primeScore = [0]*upper
        for i in range(2, upper):
            if prime[i]:
                for j in range(i, upper, i):
                    primeScore[j] += 1
                    prime[j] = False

        nextGreaterElement = [n]*n
        s = []
        for i in range(n - 1, -1, -1):
            while s and primeScore[nums[i]] >= primeScore[nums[s[-1]]]:
                s.pop()
            nextGreaterElement[i] = s[-1] if s else n
            s.append(i)

        prevGreaterOrEqualElement = [-1]*n
        s = []
        for i in range(n):
            while s and primeScore[nums[i]] > primeScore[nums[s[-1]]]:
                s.pop()
            prevGreaterOrEqualElement[i] = s[-1] if s else -1
            s.append(i)

        res = 1
        tuples = [[nums[i], i] for i in range(n)]
        tuples.sort(reverse=True)
        for num, idx in tuples:
            operations = min((idx - prevGreaterOrEqualElement[idx]) * (nextGreaterElement[idx] - idx), k)
            res = (res * self.pow(num, operations)) % self.MOD
            k -= operations
            if k == 0:
                return res

        return res

    def pow(self, x: int, n: int) -> int:
        res = 1
        while n > 0:
            if n % 2 == 1:
                res = (res * x) % self.MOD
            x = (x * x) % self.MOD
            n //= 2
        return res