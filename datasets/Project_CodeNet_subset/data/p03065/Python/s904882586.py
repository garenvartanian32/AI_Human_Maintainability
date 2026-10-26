def gcd(x,y):
    if x<y:
        x,y=y,x
    if y==0:
        return x
    if x%y==0:
        return y
    else:
        return gcd(x%y,y)
N=int(input())
a=[int(input()) for i in range(N+1)][::-1]
#N=10000
#a=[i+1 for i in range(N+1)]
g=abs(a[0])
for i in range(1,N+1):
    g=gcd(g,abs(a[i]))
def check(P):
    b=[a[i]%P for i in range(1,N+1)]
    if a[0]%P!=0:
        return False
    if N<P:
        for i in b:
            if i!=0:
                return False
        return True
    else:
        c=[0 for i in range(P-1)]
        for i in range(N):
            c[i%(P-1)]+=b[i]
            c[i%(P-1)]%=P
        for i in c:
            if i!=0:
                return False
        return True
Plist=set()
X=[1 for i in range(max(N+1,3))]
X[0]=0;X[1]=0
i=2
while(i*i<=N):
    for j in range(i,N+1,i):
        if i==j:
            if X[i]==0:
                break
        else:
            X[j]=0
    i+=1
i=2
tmp=g
while(i*i<=g):
    if tmp%i==0:
        Plist.add(i)
        while(1):
            if tmp%i==0:
                tmp=tmp//i
            else:
                break
    i+=1
if tmp>1:
    Plist.add(tmp)
for j in range(N+1):
    if X[j]==1:
        Plist.add(j)
Plist=sorted(list(Plist))
for p in Plist:
    if check(p):
        print(p)
    