


for k in range(int(input())):
    l,r,d = map(int,input().split())



    if d==1:
        if l==1:
            print(r+1)
        else:
            print(1)
    else:
        if l>=d:
            if l%d==0:
                if l//d>1:
                    print(d)
                else:
                    print(((r//d)+1)*d)
            else:
                print(d)
        else:
            print(((r//d)+1)*d)
