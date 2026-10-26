# E
N, D = map(int, input().split())
A_list = list(map(int, input().split()))

# minimum spanning tree

res = 0

# Prim based
B_list = [0]*N
for i in range(N):
    B_list[i] = A_list[i] + D*i
    
C_list = [0]*N
for i in range(N):
    C_list[i] = A_list[i] + D*(N-i)
    
# cummin seen from left
B_cummmin = [0]*N
R = B_list[N-1]
BA = N-1
for i in range(N-1, -1, -1):
    if B_list[i] <= R:
        R = B_list[i]
        BA = i
    B_cummmin[i] = BA

# cummin seen from right
C_cummmin = [0]*N
R = C_list[0]
CA = 0
for i in range(N):
    if C_list[i] <= R:
        R = C_list[i]
        CA = i
    C_cummmin[i] = CA

# start from 0
start = 0
while start < N-1:
    end = B_cummmin[start+1]
    target = C_cummmin[start]
    res += D*(end - target) + A_list[target] + A_list[end]
    for i in range(start+1, end):
        ds = D*(i - target) + A_list[target] + A_list[i]
        de = D*(end - i) + A_list[end] + A_list[i]
        res += min(ds, de)
    start = end

print(res)