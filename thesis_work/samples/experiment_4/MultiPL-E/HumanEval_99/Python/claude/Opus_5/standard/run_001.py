from decimal import Decimal, ROUND_HALF_UP

def closest_integer(value):
    number = Decimal(value)
    return int(number.to_integral_value(rounding=ROUND_HALF_UP))