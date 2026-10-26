from math import ceil
a,b,c = map(int,input().split())
t = (a*c - c*b)/b
print(ceil(t))