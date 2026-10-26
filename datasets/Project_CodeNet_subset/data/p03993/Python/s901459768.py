#!/usr/bin/env python3
n = int(input())
a = list(map(int, input().split()))
like = [0] * n
for i in range(n):
    like[i] = a[i] - 1  # 0idx
ans = 0
for i in range(n):
    if like[like[i]] == i:
        ans += 1
print(ans // 2)
