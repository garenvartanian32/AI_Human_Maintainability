n,m=map(int,input().split())
edg1=[0]*(n+1)
edgn=[0]*(n+1)
for i in range(m):
    a,b=map(int,input().split())
    if a==1:
        edg1[b]=1
    if b==n:
        edgn[a]=1

f=1
for i in range(2,n+1):
    if edg1[i]==1 and edgn[i]==1:
        f=0
print("POSSIBLE" if f==0 else "IMPOSSIBLE")
