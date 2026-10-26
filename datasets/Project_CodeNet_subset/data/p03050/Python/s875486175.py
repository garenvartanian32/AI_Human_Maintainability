from math import sqrt
n = int(input())
div = []
ans = 0
for i in range(1, int(sqrt(n))+1):
	if n%i == 0 and (n//i) - i > 1:
		ans += (n//i) - 1
print(ans)