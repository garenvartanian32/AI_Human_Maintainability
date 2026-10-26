E = ["S","H","C","D"]
n=int(input())
x=[[0 for j in range(14)]for i in range(4)]
for i in range(n):
	a,b=input().split()
	a=E.index(a)
	b=int(b)
	x[a][b]=1
	
for z in range(4):
	for c in range(1,14):
		if x[z][c]==0:
			print(f"{E[z]} {c}")
