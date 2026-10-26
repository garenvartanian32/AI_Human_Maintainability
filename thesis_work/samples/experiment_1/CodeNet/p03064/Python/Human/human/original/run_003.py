import numpy as np

N = int(input())
a = sorted([int(input()) for _ in range(N)])

s = sum(a)
m = (s + 1) // 2
m1 = m + 1


def func():
    ab = np.zeros((N + 1, m1), dtype='int64')
    ab[0, m] = 1
    for i in range(N):
        c = a[i]
        ab[i + 1, :-c] = (2 * ab[i, :-c] + ab[i, c:]) % 998244353
        ab[i + 1, -c:] = (2 * ab[i, -c:] + ab[i, m]) % 998244353
    return ab[N, 0]


def func1():
    ab = np.zeros((N + 1, m1), dtype='int64')
    ab[0, m] = 1
    for i in range(N):
        c = a[i]
        ab[i + 1, :-c] = (ab[i, :-c] + ab[i, c:]) % 998244353 
        ab[i + 1, -c:] = ab[i, -c:]
    return ab[N, 0]


ans = 3 ** N % 998244353 - 3 * func()
if s % 2 == 0:
     ans += 3 * func1()
print(ans % 998244353)