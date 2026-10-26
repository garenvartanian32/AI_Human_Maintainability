#from fractions import gcd
#mod = 10 ** 9 + 7
#N = int(input())
#a = list(map(int,input().split()))
a,b,c = map(int,input().split())
tmp = 0
flag = 0
for i in range(100):
    tmp += a
    if tmp % b == c:
        flag = 1
        break

if flag == 1:
    print('YES')
else:
    print('NO')

