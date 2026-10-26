N, M = map(int, input().split())

lr = []
for _ in range(M):
    lr.append(tuple(map(int, input().split())))

ll, rr = lr[0]
if len(lr) == 1:
    print(rr - ll + 1)
    exit(0)
for l, r in lr[1:]:
    if rr < l:
        print(0)
        exit(0)
    if ll <= l <= rr:
        ll = l

    if r < ll:
        print(0)
        exit(0)
    if ll <= r <= rr:
        rr = r

print(rr - ll + 1)
