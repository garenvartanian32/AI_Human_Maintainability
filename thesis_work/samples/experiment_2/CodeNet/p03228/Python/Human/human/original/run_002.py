A, B, K = map(int,input().split())

for i in range(K):

    if i % 2 == 0:
        B += A // 2
        A = A // 2
    else:
        A += B // 2
        B = B // 2

print("{0} {1}".format(A, B))