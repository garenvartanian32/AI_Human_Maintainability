n, k, q = [int(item) for item in input().split()]
a = [int(item) for item in input().split()] + [-1]
ans = 10**15
for min_val in a[:-1]:
    takeble = []
    l, r = -1, -1 
    for i, item in enumerate(a):
        if item >= min_val and l == -1:
            l = i
        elif item < min_val and l != -1:
            r = i
            takeble.append(a[l:r])
            l = -1
    taken = []
    for part in takeble:
        if len(part) < k:
            continue
        part.sort()
        taken += part[:len(part)-k+1]
    if len(taken) < q:
        continue
    taken.sort()
    ans = min(ans, taken[q-1] - taken[0])
print(ans)