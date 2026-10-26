class Solution:
    def maximumScore(self, nums: List[int], k: int) -> int:
        def prime_score(x):
            factors = set()
            while x % 2 == 0:
                factors.add(2)
                x //= 2
            for i in range(3, int(x ** 0.5) + 1, 2):
                while x % i == 0:
                    factors.add(i)
                    x //= i
            if x > 2:
                factors.add(x)
            return len(factors)
        
        max_score = 1
        MOD = 10 ** 9 + 7
        
        pq = []  # Priority queue to store elements by their prime scores
        
        for num in nums:
            heapq.heappush(pq, (-prime_score(num), num))  # Use a negative score to get the maximum prime score first
        
        while k > 0:
            prime_score, num = heapq.heappop(pq)  # Pop the element with the highest prime score
            max_score = (max_score * num) % MOD
            k -= 1
        
        return max_score