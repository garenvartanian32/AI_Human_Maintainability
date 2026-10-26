n,m=map(int,input().split())
s=[list(map(int,input().split())) for _ in range(n)]
m=[list(map(int,input().split())) for _ in range(m)]
for i in s:
  a,b=i[0],i[1]
  md=[abs(c[0]-a)+abs(c[1]-b) for c in m]
  print(md.index(min(md))+1)