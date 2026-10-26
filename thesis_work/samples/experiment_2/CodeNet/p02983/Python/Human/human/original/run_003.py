l,r=map(int,input().split())

ans=10000
for i in range(l,l+min(r-l,2020)):
  for j in range(i+1,l+min(r-l,2020)+1):
    ans=min(ans,(i*j)%2019)

print(ans)