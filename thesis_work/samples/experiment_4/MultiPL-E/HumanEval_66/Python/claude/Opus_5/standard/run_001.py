def digitSum(s):
    return sum(ord(char) for char in s if "A" <= char <= "Z")