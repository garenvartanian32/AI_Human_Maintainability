A, B, K = map(int, input().split())
C = [A, B]
for i in range(K):
    if C[i&1] & 1:
        C[i&1] -= 1
    C[i&1] >>= 1
    C[(i&1)^1] += C[i&1]
print(*C)