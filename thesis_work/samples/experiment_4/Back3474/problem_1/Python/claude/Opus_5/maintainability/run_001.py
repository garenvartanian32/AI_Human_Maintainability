"""Maximum sum of a pair of numbers sharing the same largest digit."""


def largest_digit(number: int) -> int:
    """Return the greatest decimal digit appearing in ``number``."""
    return max(int(character) for character in str(number))


class Solution:
    def maxSum(self, nums: list[int]) -> int:
        """Return the best pair sum among numbers with equal largest digit.

        Numbers are grouped by their largest digit. Within a group any two
        members form a valid pair, so the best pair is simply the two
        largest members of that group.
        """
        NO_PAIR_FOUND = -1

        # For each largest digit remember the biggest value seen so far.
        best_per_digit: dict[int, int] = {}
        best_sum = NO_PAIR_FOUND

        for value in nums:
            digit = largest_digit(value)
            previous_best = best_per_digit.get(digit)

            if previous_best is not None:
                best_sum = max(best_sum, previous_best + value)
                best_per_digit[digit] = max(previous_best, value)
            else:
                best_per_digit[digit] = value

        return best_sum