def triples_sum_to_zero(l: list):
    """
    triples_sum_to_zero takes a list of integers as an input.
    it returns True if there are three distinct elements in the list that
    sum to zero, and False otherwise.

    >>> triples_sum_to_zero([1, 3, 5, 0])
    False
    >>> triples_sum_to_zero([1, 3, -2, 1])
    True
    >>> triples_sum_to_zero([1, 2, 3, 7])
    False
    >>> triples_sum_to_zero([2, 4, -5, 3, 9, 7])
    True
    >>> triples_sum_to_zero([1])
    False
    """
    # Sorting lets each first element be paired off with a two-pointer sweep,
    # which finds a matching pair in linear time instead of quadratic.
    values = sorted(l)

    for first in range(len(values)):
        low = first + 1
        high = len(values) - 1

        while low < high:
            total = values[first] + values[low] + values[high]
            if total == 0:
                return True
            if total < 0:
                low += 1
            else:
                high -= 1

    return False