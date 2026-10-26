n,m = map(int,input().split())
l = []
for i in range(n):
    l.append(list(map(int,input().split())))
l = sorted(l)
p = 0
d = 0
i = 0
while(d < m):
    s = min(l[i][1],m-d)
    d += s
    p += l[i][0] * s
    i += 1
print(p)