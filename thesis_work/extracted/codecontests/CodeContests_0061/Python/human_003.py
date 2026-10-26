
n=int(input())
for i in range(n):
	k=0
	a,b,c=map(int,input().split())
	while(b>0 and c>1 ):
		b-=1
		c-=2
		k+=1
	while(a>0 and b>1 ):
		b-=2
		a-=1
		k+=1
	print(k*3)