n, k, q = map(int, input().split())
a = list(map(int, input().split()))

set_a = sorted(set(a))

result = 1000000000
for y in set_a:
    counter = 0
    candidate = []
    for i in range(n):
#        print(counter, a[i], y)
        if a[i] < y:
            if counter >= k:
#                print(a[i-counter:i-1])
                candidate = candidate + sorted(a[i-counter:i])[:counter - k + 1]
            counter = 0
        elif i == n - 1:
            if counter + 1 >= k:
                candidate = candidate + sorted(a[i-counter:i+1])[:counter - k + 2]
        else:
            counter += 1
#    print(candidate)
    if len(candidate) >= q:
        z = sorted(candidate)[q - 1] - y
        if z < result:
            result = z

print(result)