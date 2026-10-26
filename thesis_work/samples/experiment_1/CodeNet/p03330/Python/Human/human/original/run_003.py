n,C = map(int,input().split())
d = [[int(i) for i in input().split()] for _ in range(C)]
c = [[int(i)-1 for i in input().split()] for _ in range(n)]

cnt0 = [0]*C
cnt1 = [0]*C
cnt2 = [0]*C
for i in range(n):
    for j in range(n):
        for k in range(C):
            if (i+j)%3 == 0: cnt0[k] += d[c[i][j]][k]
            elif (i+j)%3 == 1: cnt1[k] += d[c[i][j]][k]
            else: cnt2[k] += d[c[i][j]][k]

ans = float("inf")
for i in range(C):
    for j in range(C):
        if i == j: continue
        for k in range(C):
            if i == k or j == k: continue
            ans = min(ans, cnt0[i] + cnt1[j] + cnt2[k])
print(ans)