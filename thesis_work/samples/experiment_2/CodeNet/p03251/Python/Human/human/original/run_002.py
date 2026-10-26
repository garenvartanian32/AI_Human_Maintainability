n,m,x,y=map(int,input().split())
a=list(map(int, input().split()))
b=list(map(int, input().split()))
res="War"
for i in range(x+1,y+1):
    if max(a)<i<=min(b):
        res="No War"
        break
print(res)