n = int(input())
data = input().split()

#print(str(n) + " " + str(data))

data = list(map(lambda x: int(x), data))
res = 0
ires = 0
neg = 0
when = [0] * n

for i in range(n):
	data[i] = i + 1 - data[i]
	res += abs(data[i])
	if data[i] <= 0:
		neg += 1
	a = -data[i]
	if a < 0:
		a = a + n
	when[a] += 1
#print(when)

ares = res	
#print(str(res) + " " + str(ires) + " " + str(neg))
for i in range(n):
	neg -= when[i]
	ares -= neg
	ares += (n - neg)
	x = data[n - i - 1] + i + 1
	ares -= x
	ares += n - x
	#print(str(res) + " " + str(ires) + " " + str(ares) + " " + str(i) + " " + str(neg))
	neg += 1
	if ares < res:
		res = ares
		ires = i + 1

print(str(res) + " " + str(ires))

