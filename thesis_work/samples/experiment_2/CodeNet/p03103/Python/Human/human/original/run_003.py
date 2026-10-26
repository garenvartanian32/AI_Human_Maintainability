N, M = map(int, input().split())
AB = []

for _ in range(N):
    a, b = map(int, input().split())
    AB.append([a, b])

AB = sorted(AB)


sum = 0
ans = 0
for i in range(N):
    sum += AB[i][1]
    ans += AB[i][1] * AB[i][0]
    if sum >= M:
        ans -= (sum - M) * AB[i][0]
        break

print(ans)