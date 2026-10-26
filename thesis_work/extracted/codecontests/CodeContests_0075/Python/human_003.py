n,x=map(int,input().split())
a=list(map(int,input().split()))
s=0
for i in range(1,n):
    s=max(s,a[i-1]-a[i])
if s<=0 or s-x <=0:
    print(0)
else:
    print(s-x)
    