def starts_one_ends(n):
    if n == 1:
        return 1

    numbers_with_fixed_outer_digit = 10 ** (n - 2)
    starting_with_one = 10 * numbers_with_fixed_outer_digit
    ending_with_one = 9 * numbers_with_fixed_outer_digit
    starting_and_ending_with_one = numbers_with_fixed_outer_digit

    return (
        starting_with_one
        + ending_with_one
        - starting_and_ending_with_one
    )