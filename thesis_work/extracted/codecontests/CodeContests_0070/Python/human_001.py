n=int(input())
mat=[]
for i in range(n):
    mat.append(list(map(int, input().rstrip().split())))
b=0
for i in range (n):
    for j in range (n):
        if mat[i][0]==mat[j][1]:
            b=b+1
print(b)