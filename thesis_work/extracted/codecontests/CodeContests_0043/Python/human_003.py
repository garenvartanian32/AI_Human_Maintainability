T = int(input())


def gcd(a, b):
    if b == 0:
        return a
    else:
        return gcd(b, a % b)


for i in range(T):
    a, b, c, d = map(int, input().split())
    if a < b:
        print('No')
    elif d < b:
        print('No')
    elif c >= b:
        print('Yes')
    else:
        g = gcd(b, d)
        if b - g + a % g > c:
            print('No')
        else:
            print('Yes')