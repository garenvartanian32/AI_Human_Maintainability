from decimal import Decimal, ROUND_HALF_UP

def closest_integer(value):
    number = Decimal(value)
    rounded_number = number.to_integral_value(rounding=ROUND_HALF_UP)
    return int(rounded_number)