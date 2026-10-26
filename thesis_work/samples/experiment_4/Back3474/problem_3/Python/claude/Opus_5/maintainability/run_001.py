"""Minimum absolute difference between elements at least x indices apart."""

from bisect import bisect_left, insort


class Solution:
    def minAbsoluteDifference(self, nums: list[int], x: int) -> int:
        """Return min |nums[i] - nums[j]| over all pairs with |i - j| >= x.

        A sorted window holds every element that is far enough behind the
        current index. For each position we only need the two window entries
        that bracket the current value, found by binary search.
        """
        if x == 0:
            return 0

        eligible_values: list[int] = []
        smallest_difference = float("inf")

        for index in range(x, len(nums)):
            insort(eligible_values, nums[index - x])
            current = nums[index]

            insertion_point = bisect_left(eligible_values, current)
            if insertion_point < len(eligible_values):
                smallest_difference = min(
                    smallest_difference, eligible_values[insertion_point] - current
                )
            if insertion_point > 0:
                smallest_difference = min(
                    smallest_difference, current - eligible_values[insertion_point - 1]
                )

        return smallest_difference