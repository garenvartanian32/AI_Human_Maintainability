"""Maximum score obtainable by choosing k subarrays greedily by value."""

MODULUS = 10**9 + 7
MAX_VALUE = 100_001


def _build_distinct_prime_counts(limit: int) -> list[int]:
    """Sieve giving, for every n < limit, its number of distinct prime factors."""
    counts = [0] * limit
    for candidate in range(2, limit):
        if counts[candidate] == 0:  # candidate is prime
            for multiple in range(candidate, limit, candidate):
                counts[multiple] += 1
    return counts


DISTINCT_PRIME_COUNTS = _build_distinct_prime_counts(MAX_VALUE)


class Solution:
    def maximumScore(self, nums: list[int], k: int) -> int:
        """Return the maximum score achievable with exactly k operations.

        Each element may be chosen as the "dominant" element of any subarray
        where its prime score is strictly greater than everything to its left
        and greater-or-equal to everything on its right. Counting those
        subarrays tells us how many times an element can be used; we then spend
        the k operations on the largest values first.
        """
        prime_scores = [DISTINCT_PRIME_COUNTS[value] for value in nums]
        reach = self._count_dominated_subarrays(prime_scores)

        score = 1
        operations_left = k
        for index in sorted(range(len(nums)), key=lambda i: nums[i], reverse=True):
            if operations_left == 0:
                break
            uses = min(reach[index], operations_left)
            score = score * pow(nums[index], uses, MODULUS) % MODULUS
            operations_left -= uses

        return score

    @staticmethod
    def _count_dominated_subarrays(prime_scores: list[int]) -> list[int]:
        """For each index, how many subarrays it dominates by prime score."""
        length = len(prime_scores)

        # Nearest index to the left with a strictly greater score.
        previous_greater = [-1] * length
        stack: list[int] = []
        for index in range(length):
            while stack and prime_scores[stack[-1]] < prime_scores[index]:
                stack.pop()
            previous_greater[index] = stack[-1] if stack else -1
            stack.append(index)

        # Nearest index to the right with a greater-or-equal score.
        next_greater_or_equal = [length] * length
        stack.clear()
        for index in reversed(range(length)):
            while stack and prime_scores[stack[-1]] <= prime_scores[index]:
                stack.pop()
            next_greater_or_equal[index] = stack[-1] if stack else length
            stack.append(index)

        return [
            (index - previous_greater[index]) * (next_greater_or_equal[index] - index)
            for index in range(length)
        ]