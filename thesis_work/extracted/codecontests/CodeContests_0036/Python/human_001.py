input()
a=list(map(int,input().split()))
ans=0
for x in a:
    z=min(x-1,1000000-x)
    ans=max(z,ans)
print(ans)
