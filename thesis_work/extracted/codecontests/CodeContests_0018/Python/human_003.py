a,b,c=map(int,input().split())
if a==0:
    if b==0:
        if c==0:
            print(-1)
        else:
            print(0)
    else:
        print(1)
        print(-c/b)
else:
    d=b**2-4*a*c
    if d>0:
        x1=(-b-d**0.5)/(2*a)
        x2=(-b+d**0.5)/(2*a)
        print(2)
        if x1<x2:
            print(x1)
            print(x2)
        else:
            print(x2)
            print(x1)
    elif d==0:
        print(1)
        print(-b/(2*a))
    else:
        print(0)
