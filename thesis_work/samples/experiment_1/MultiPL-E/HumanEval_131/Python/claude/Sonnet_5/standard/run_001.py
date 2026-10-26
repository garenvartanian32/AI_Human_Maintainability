def digits(n):
    """Given a positive integer n, return the product of the odd digits.
    Return 0 if all digits are even.
    For example:
    digits(1)  == 1
    digits(4)  == 0
    digits(235) == 15
    """
    digs = [int(d) for d in str(n) if int(d) % 2 == 1]
    if not digs:
        return 0
    p = 1
    for d in digs:
        p *= d
    return p