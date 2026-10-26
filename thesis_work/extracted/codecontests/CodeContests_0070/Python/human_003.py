n=int(input())

a=[[0 for col in range(2)] for row in range(n)]
for i in range(n):
    a[i]=[int(i) for i in input().split()]

s=0
for i in range(n):
    for j in range(i+1,n):
        s=s+(a[i][0]==a[j][1])+(a[i][1]==a[j][0])

print(s)
        
