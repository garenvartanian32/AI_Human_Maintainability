n = int(input())
*A, = map(int, input().split())
ans = 0
for i in range(n):
    j = A[i:].index(min(A[i:])) + i
    A[i], A[j] = A[j], A[i]
    if i != j:
        ans += 1
print(*A)
print(ans)