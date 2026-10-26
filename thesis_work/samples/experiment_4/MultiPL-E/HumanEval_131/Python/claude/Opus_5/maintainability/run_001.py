def digits(n):
    """Given a positive integer n, return the product of the odd digits.
    Return 0 if all digits are even.
    For example:
    digits(1)  == 1
    digits(4)  == 0
    digits(235) == 15
    """
    odd_digits = [int(character) for character in str(n) if int(character) % 2 == 1]

    # An empty product would be 1, but the specification asks for 0 instead.
    if not odd_digits:
        return 0

    product = 1
    for digit in odd_digits:
        product *= digit
    return product