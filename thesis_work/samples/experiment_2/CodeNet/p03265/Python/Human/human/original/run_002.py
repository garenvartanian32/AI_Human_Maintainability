x1, y1, x2, y2 = [int(_) for _ in input().split()]
p = (x1 + x2 + y1 - y2) / 2
q = (x2 - x1 + y2 + y1) / 2
v = x1 - p
u = y1 - q
x3, y3 = int(p - v), int(q - u)
x4, y4 = int(p + u), int(q - v)
print(x3, y3, x4, y4)