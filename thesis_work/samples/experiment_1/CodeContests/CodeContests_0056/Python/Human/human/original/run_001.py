import math
n = int(input())
l= list(map(int,input().split()))
s = 2*sum(l)
z= s/n
p = max(l)
an = int(z+1)
print(max(p,an))