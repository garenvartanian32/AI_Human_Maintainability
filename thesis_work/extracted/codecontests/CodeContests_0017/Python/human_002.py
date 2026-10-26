# https://codeforces.com/problemset/problem/168/A
# 900

n, x, y = map(int, input().split())
y /= 100

c = 0
while True:
    if (x+c) / n >= y:
        break
    
    c += 1

print(c)