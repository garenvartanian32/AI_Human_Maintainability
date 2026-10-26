N=int(input())
A=list(map(int,input().split()))
B=list(map(int,input().split()))
C=list(map(int,input().split()))

ans=0
p=-100
for i in A:
	ans+=B[i-1]
	if (p+1)==i:
		ans+=C[p-1]
	p=i

print(ans)