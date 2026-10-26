def is_magic_square(a,b,c,d,r1,r2,c1,c2,d1,d2):
    return ((a+b==r1) and (c+d==r2) and (a+c==c1) and (b+d==c2) and (a+d==d1) and (b+c==d2))
r1,r2 = [int(i) for i in input().split()]
c1,c2 = [int(i) for i in input().split()]
d1,d2 = [int(i) for i in input().split()]
flag = False
for a in range(1,10):
    for b in range(1,10):
        for c in range(1,10):
            for d in range(1,10):
                if a==b or a==c or a==d or b==c or b==d or c==d:
                    continue
                if is_magic_square(a,b,c,d,r1,r2,c1,c2,d1,d2):
                    flag = True
                    print(a,b)
                    print(c,d)
                    break
            if flag:
                break
        if flag:
            break
    if flag:
        break
if not(flag):
    print(-1)