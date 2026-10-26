n=int(input())
deg=[0]*n
for i in range(n-1):
    u,v=map(int,input().split())
    u-=1
    v-=1
    deg[u]+=1
    deg[v]+=1
for d in deg:
    if d==2:
        print("NO")
        exit(0)
print("YES")