r1, r2 = list(map(int, input().rstrip().split()))
c1, c2 = list(map(int, input().rstrip().split()))
d1, d2 = list(map(int, input().rstrip().split()))
li = [1, 2, 3, 4, 5, 6, 7, 8, 9]
flag = 0
for i in li:
    a = i
    c = c1 - a
    b = r1 - a
    d = d1 - a
    
    if c in li and b in li and d in li:
        if (b == d2 - c) and (b == c2 - d) and(c+d==r2):
            flag=1
            break
if a == b or a == c or a == d or b == c or b == d or c == d:
    flag=0
if flag == 1:
    print(a, b)
    print(c, d)
else:
    print(-1)
