import sys

sys.setrecursionlimit(10 ** 7)
f_inf = float('inf')
mod = 10 ** 9 + 7


def make_divisors(n):
    divisors = []
    for i in range(1, int(pow(n, 0.5)) + 1):
        if n % i == 0:
            divisors.append(i)
            if i != n // i:
                divisors.append(n // i)
    divisors.sort()
    return divisors


def resolve():
    n = int(input())

    div = make_divisors(n)
    res = 0
    for i in div:
        if i != 1:
            x, y = divmod(n, i - 1)
            if x == y:
                res += i - 1

    print(res)


if __name__ == '__main__':
    resolve()
