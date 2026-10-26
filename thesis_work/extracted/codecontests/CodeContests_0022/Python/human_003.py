def toSys(n, k):
    res = ''
    t = k
    while (n != 0):
        res += str(n % t)
        n //= t
        t *= k 
    return res[::-1]


n = int(input())
for i in range(1, n):
    for j in range(1, n):
        print(toSys(i * j, n), end=" ")
    print()