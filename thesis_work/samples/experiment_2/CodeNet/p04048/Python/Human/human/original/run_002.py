n, x = map(int, input().split())

def func(a, b):
    res = 0
    while b > 0:
        c, d = divmod(a, b)
        res += 2 * b * c
        a = b
        b = d
    
    return res - a

print(n + func(x, n-x))