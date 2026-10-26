n=int(input())
A=list(map(int,input().split()))
cnt=0
for i in range(n-1):
  minj=i
  for j in range(i,n):
    if A[minj]>A[j]:
      minj=j
  if minj!=i:
    A[i],A[minj]=A[minj],A[i]
    cnt+=1
print(*A)
print(cnt)
