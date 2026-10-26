def digitSum(s):
    total_ascii_value = 0

    for character in s:
        if "A" <= character <= "Z":
            total_ascii_value += ord(character)

    return total_ascii_value