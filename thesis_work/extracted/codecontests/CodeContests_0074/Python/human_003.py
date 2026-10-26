n, m = map(int, input().split())
a = [int(1e9)] * n
extra = [0] * n
query = list()
for _ in range(m):
    t, l, r, x = map(int, input().split())
    l -= 1
    r -= 1
    query.append((t, l, r, x))
    if t == 1:
        for j in range(l, r+1):
            extra[j] += x
    else:
        for j in range(l, r+1):
            a[j] = min(a[j], x - extra[j])

extra = a[:]
for t, l, r, x in query:
    if t == 1:
        for j in range(l, r+1):
            a[j] += x
    else:
        val = -10**9
        for j in range(l, r+1):
            val = max(val, a[j])
        if not val == x:
            print('NO')
            exit()

print('YES')
print(*extra)
