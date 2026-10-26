n,m=map(int,input().split())
a=[]
c=[]
for i in range(n):
    a.append(list(map(int,input().split())))
for i in range(m):
    c.append(list(map(int,input().split())))

for k in range(n):
    tmp=[]
    for j in range(m):
        tmp.append(abs(a[k][0]-c[j][0])+abs(a[k][1]-c[j][1]))
    print(tmp.index(min(tmp))+1)

