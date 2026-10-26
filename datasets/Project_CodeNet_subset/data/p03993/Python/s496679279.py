N = int(input())

A = [int(x) - 1 for x in input().split()]

cnt = 0
for i in range(N):
    if A[A[i]] == i:
        cnt += 1

print(cnt//2)