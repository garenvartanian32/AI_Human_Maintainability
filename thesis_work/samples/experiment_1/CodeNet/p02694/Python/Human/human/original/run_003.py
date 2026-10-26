import math
n = int(input())

a = 100
c = 0
while a < n:
  c += 1
  a = a*1.01
  a = int(a)
  
print(c)