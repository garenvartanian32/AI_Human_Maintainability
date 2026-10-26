from itertools import product
n=int(input())
f=[list(map(int,input().split())) for _ in range(n)]
p=[list(map(int,input().split())) for _ in range(n)]
cd=[]

for i in product([0,1],repeat=10):
  if i==(0,0,0,0,0,0,0,0,0,0):continue
  s=0
  for (idx,j) in enumerate(f):
    s+=p[idx][sum(k*l for (k,l) in zip(i,j))]
  cd.extend([s])
print(max(cd))

