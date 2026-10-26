import cmath
EPS = 1e-10

#外積
def OuterProduct(one, two):
	tmp = one.conjugate() * two
	return tmp.imag

#3点が反時計回りか
#一直線上のときの例外処理できていない→とりあえずT
def CCW(p, q, r):
	one, two = q-p, r-q
	if OuterProduct(one, two) > -EPS:
		return True
	else:
		return False

#凸包の直径
def ConvexDiameter(qs):
	n = len(qs)
	if n == 2:
 		return abs(qs[0]-qs[1])
	i, j = 0, 0
	for k in range(n):
		if qs[k].real < qs[i].real:
			i = k
		if qs[j].real < qs[k].real:
			j = k
	res = 0
	si, sj = i, j
	while i != sj or j != si:
		res = max(res, abs(qs[i]-qs[j]))
		if OuterProduct(qs[i-n+1] - qs[i], qs[j-n+1] - qs[j]) < -EPS:
			i = (i+1) % n
		else:
			j = (j+1) % n
	return res

n = int(input())
dots = []
for _ in range(n):
	x, y = map(float, input().split())
	dots.append(complex(x, y))

print("{:.12f}".format(ConvexDiameter(dots)))
