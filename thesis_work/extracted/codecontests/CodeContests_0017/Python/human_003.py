import math
n,x,y=map(int,input().split())
z=math.ceil((y/100)*n)
if(x>=z):
	print(0)
else:
	print(z-x)