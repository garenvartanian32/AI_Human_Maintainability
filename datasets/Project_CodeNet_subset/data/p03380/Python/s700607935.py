import itertools
import math

n=int(input())
a=list(map(int,input().split(" ")))

#n=5
#a=[6,9,4,2,11]

a1=max(a)
a.remove(a1)
a2=a1/2
b=[]
for i in a:
  b.append(abs(i-a2))

print(a1,a[b.index(min(b))])