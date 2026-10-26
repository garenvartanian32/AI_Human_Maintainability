W=[]
K=[]
for i in range(10):
    s=int(input())
    W.append(s)
for j in range(10):
    t=int(input())
    K.append(t)
W=sorted(W)
K=sorted(K)
print(f'{sum(W[7:10])} {sum(K[7:10])}')
