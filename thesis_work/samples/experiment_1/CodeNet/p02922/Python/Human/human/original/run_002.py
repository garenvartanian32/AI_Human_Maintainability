a,b=map(int,input().split())
n=1
ans=0
while n<b:
    n=n-1+a
    ans+=1

print(ans)