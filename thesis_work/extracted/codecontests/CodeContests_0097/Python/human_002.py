n=int(input())
a=list(map(int,input().split()))
b=list(map(int,input().split()))
c=list(map(int,input().split()))
f=0
t=-1
for i in range(n):
 if t == a[i]-1:
  f+=c[t-1]
 f+=b[a[i]-1]
 t=a[i]
print(f)