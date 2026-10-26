
n, c = map(int, input().split())
l = list(map(int, input().split()))

ans = 0
for i in range(n - 1):
	d = l[i] - l[i + 1] - c
	ans = max(ans, d)


print(ans)