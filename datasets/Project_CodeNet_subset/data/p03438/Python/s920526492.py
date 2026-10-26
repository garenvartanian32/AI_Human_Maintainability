N,*f = map(int, open(0).read().split())
ab = [f[i] - f[i+N] for i in range(N)]
c = 0
d = 0
for x in ab:
    if x < 0:
        c += abs(x) // 2
    elif x > 0:
        d += x
if c >= d:
    print('Yes')
else:
    print('No')