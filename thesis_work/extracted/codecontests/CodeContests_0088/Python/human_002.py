s = int(input())
a = hex(s)[2:]
print(a.count('0') + a.count('4') + a.count('6') + a.count('8') * 2 + a.count('a') + a.count('9') + a.count('b') * 2 + a.count('d'))