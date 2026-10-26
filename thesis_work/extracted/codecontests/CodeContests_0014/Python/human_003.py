from bisect import*
I=lambda:map(int,input().split())
for _ in range(*I(),):
	n,m=I();p=[0];M=[0]
	for v in I():p+=p[-1]+v,;M+=max(M[-1],p[-1]),
	s=p[-1];a=[]
	for x in map(int,input().split()):
		r=0
		if s>0:t=max((x-M[-1]+s-1)//s,0);r=t*n;x-=t*s
		a+=[str(r+bisect_left(M,x)-1),'-1'][x>M[-1]],
	print(*a)