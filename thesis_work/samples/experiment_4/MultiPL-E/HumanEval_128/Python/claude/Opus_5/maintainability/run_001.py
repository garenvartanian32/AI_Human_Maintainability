def prod_signs(arr):
    """
    You are given an array arr of integers and you need to return
    sum of magnitudes of integers multiplied by product of all signs
    of each number in the array, represented by 1, -1 or 0.
    Note: return None for empty arr.

    Example:
    >>> prod_signs([1, 2, 2, -4]) == -9
    >>> prod_signs([0, 1]) == 0
    >>> prod_signs([]) == None
    """
    if not arr:
        return None

    # A single zero makes the whole sign product zero.
    if any(value == 0 for value in arr):
        return 0

    # Otherwise the sign is negative exactly when an odd number of factors are.
    negative_count = sum(1 for value in arr if value < 0)
    total_magnitude = sum(abs(value) for value in arr)

    return -total_magnitude if negative_count % 2 else total_magnitude