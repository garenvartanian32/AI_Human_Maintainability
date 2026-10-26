x = int(input())
s = 100
y = 0
while s < x:
    if y == 0:
        s = 100 + 1
    else:
        s = s + int(s / 100)
    y += 1

print(y)
