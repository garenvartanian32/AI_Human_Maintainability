def prime_length(string):
    length = len(string)

    if length < 2:
        return False

    candidate_divisor = 2
    while candidate_divisor * candidate_divisor <= length:
        if length % candidate_divisor == 0:
            return False
        candidate_divisor += 1

    return True