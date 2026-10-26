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
    l = sorted(l)
    n = len(l)
    for i in range(n-2):
        lo, hi = i+1, n-1
        while lo < hi:
            s = l[i] + l[lo] + l[hi]
            if s == 0:
                return True
            elif s < 0:
                lo += 1
            else:
                hi -= 1
    return False