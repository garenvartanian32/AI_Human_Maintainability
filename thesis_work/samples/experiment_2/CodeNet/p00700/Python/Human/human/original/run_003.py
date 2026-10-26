for _ in range(int(input())):
    m=dx=dy=0
    while 1:
        x,y=map(int,input().split())
        if x==y==0:break
        dx+=x
        dy+=y
        l=dx*dx+dy*dy
        if (m==l and mx<dx) or m<l:m,mx,my=l,dx,dy
    print(mx,my)