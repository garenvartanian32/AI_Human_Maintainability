n = int(input())
a = list(input().split(' '))
a = list(int(x) for x in a)
one, two = 0, 0
for i in range(n):
    if a[i] == 100:
        one += 1
    else:
        two += 1
flag = False
if one%2 == 0 and two%2 == 0 or one > two and two % 2 == 1 and one % 2 == 0 and one >= 2 \
   or one < two and two%2 == 1 and one%2 == 0 and one >= 2:
    flag = True
if not flag:
    print('NO')
else:
    print('YES')
