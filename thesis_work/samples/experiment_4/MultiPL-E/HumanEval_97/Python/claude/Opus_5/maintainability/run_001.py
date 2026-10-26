def multiply(a, b):
    first_unit_digit = abs(a) % 10
    second_unit_digit = abs(b) % 10
    return first_unit_digit * second_unit_digit