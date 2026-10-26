n,k=map(int,input().split())
a=[int(i) for i in input().split()]
import math
h=round(math.sqrt(k*2))
for i in range(h,-1,-1):
    if(k>round(i*(i+1)/2)):
        break
    else:
        h-=1
k=k-round(h*(h+1)/2)
if(k==0):
    print(a[len(a)-1])
else:
    print(a[k-1])