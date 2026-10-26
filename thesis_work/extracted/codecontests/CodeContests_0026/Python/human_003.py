n = int(input())
x = [0] * n
y = [0] * n
for i in range(n):
    x[i], y[i], r = map(int, input().split())


def sum(s1, s2):
    return '(' + s1 + '+' + s2 + ')'


def minus(s1, s2):
    return '(' + s1 + '-' + s2 + ')'


def mult(s1, s2):
    return '(' + s1 + '*' + s2 + ')'


def sabs(s1):
    return 'abs(' + s1 + ')'


def stand(x):
    return sum(minus('1', sabs(minus('t', x))), sabs(minus(sabs(minus('t', x)), '1')))


def ans(v):
    s=''
    for i in range(1, n + 1):
        if s == '':
            s = mult(str(v[i - 1] // 2), stand(str(i)))
        else:
            s = sum(s, mult(str(v[i - 1] // 2), stand(str(i))))
    print(s)


ans(x)
ans(y)
