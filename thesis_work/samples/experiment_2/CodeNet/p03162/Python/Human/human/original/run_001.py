def retMax(a,b):
  if a>=b:
    return a
  else:
    return b

n = int(input())
r = []
dpList = [ [0] * 3 for x in range(n)]

for i in range(n):
  r.append(list(map(int,input().split())))

dpList[0][0] = r[0][0]  
dpList[0][1] = r[0][1]  
dpList[0][2] = r[0][2]  

for s in range(1,n):
  #b->a,c->aの最大値
  dpList[s][0] = retMax(dpList[s-1][1]+r[s][0],dpList[s-1][2]+r[s][0])
  #a->b,c->bの最大値
  dpList[s][1] = retMax(dpList[s-1][0]+r[s][1],dpList[s-1][2]+r[s][1])
  #a->c,b->cの最大値
  dpList[s][2] = retMax(dpList[s-1][0]+r[s][2],dpList[s-1][1]+r[s][2])

print(max(dpList[n-1]))
  