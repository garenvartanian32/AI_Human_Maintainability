n, k = [int (i) for i in input().split()]
bucks = [int (i) for i in input().split()]
m = 100
for i in bucks:
    if k % i == 0 and k // i < m:
        m = k // i
print(m)