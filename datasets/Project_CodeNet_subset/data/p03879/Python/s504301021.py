x1, y1 = map(int, input().split())
x2, y2 = map(int, input().split())
x3, y3 = map(int, input().split())
a = (((x1 - x2) ** 2) + ((y1 - y2) ** 2)) ** 0.5
b = (((x3 - x2) ** 2) + ((y3 - y2) ** 2)) ** 0.5
c = (((x1 - x3) ** 2) + ((y1 - y3) ** 2)) ** 0.5
max_s = max(max(a, b), c)
s = (a + b + c) / 2
S = (s * (s - a) * (s - b) * (s - c)) ** 0.5
r = S / s
print(r * max_s / (max_s + (2 * r)))