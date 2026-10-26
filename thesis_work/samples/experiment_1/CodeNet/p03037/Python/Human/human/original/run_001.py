N,M = map(int,input().split())
L=[0]*M
R=[0]*M
for i in range(M):
    L[i],R[i] = map(int,input().split())

# N,M=10,3
# L=[1,2]
# R=[3,4]
if min(R)-max(L)+1 < 0:
    print(0)
else:
    print(min(R)-max(L)+1)