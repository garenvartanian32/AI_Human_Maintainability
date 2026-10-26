from scipy.sparse.csgraph import floyd_warshall as ws
import numpy as np

N,M=list(map(int,input().split()))
dist=np.array([[10**9 for _ in range(N)] for _ in range(N)])
for i in range(N):
    dist[i,i]=0

for i in range(M):
    a,b,c=list(map(int,input().split()))
    dist[a-1,b-1]=c
    dist[b-1,a-1]=c

wsdist=np.array(ws(dist),dtype='int')
ans=-N
for i in range(N):
    for j in range(N):
        if wsdist[i][j]==dist[i][j]:
            ans+=1
print(M-ans//2)