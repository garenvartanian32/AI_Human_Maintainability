import sys
pi = 3.1415926535897932384626

def input():
	return sys.stdin.buffer.readline()[:-1]

class Rmax():
	def __init__(self, size):
		#the number of nodes is 2n-1
		self.n = 1
		while self.n < size:
			self.n *= 2
		self.node = [0] * (2*self.n-1)

	def Access(self, x):
		return self.node[x+self.n-1]

	def Update(self, x, val):
		x += self.n-1
		self.node[x] = val
		while x > 0:
			x = (x-1)//2
			self.node[x] = max(self.node[2*x+1], self.node[2*x+2])
		return

	#[l, r)
	def Get(self, l, r):
		L, R = l+self.n, r+self.n
		s = 0
		while L<R:
			if R & 1:
				R -= 1
				s = max(s, self.node[R-1])
			if L & 1:
				s = max(s, self.node[L-1])
				L += 1
			L >>= 1
			R >>= 1
		return s

x1, y1, x2, y2 = map(int, input().split())
n = int(input())
f = [tuple(map(int, input().split())) for _ in range(n)]

if x1 == x2:
	flg = False
	for x, y in f:
		if x == x1 and min(y1, y2) <= y <= max(y1, y2):
			flg = True
	if flg:
		print(abs(y1-y2) * 100 + 10*pi - 20)
	else:
		print(abs(y1-y2) * 100)
elif y1 == y2:
	flg = False
	for x, y in f:
		if y == y1 and min(x1, x2) <= x <= max(x1, x2):
			flg = True
	if flg:
		print(abs(x1-x2) * 100 + 10*pi - 20)
	else:
		print(abs(x1-x2) * 100)
else:
	if x1 > x2 and y1 > y2:
		x1, x2, y1, y2 = x2, x1, y2, y1
		f = [[x[0], x[1], i] for i, x in enumerate(f) if min(x1, x2) <= x[0] <= max(x1, x2) and min(y1, y2) <= x[1] <= max(y1, y2)]
	elif x1 > x2:
		x1, x2 = 10**8-x1, 10**8-x2
		f = [[10**8-x[0], x[1], i] for i, x in enumerate(f) if min(x1, x2) <= 10**8-x[0] <= max(x1, x2) and min(y1, y2) <= x[1] <= max(y1, y2)]
	elif y1 > y2:
		y1, y2 = 10**8-y1, 10**8-y2
		f = [[x[0], 10**8-x[1], i] for i, x in enumerate(f) if min(x1, x2) <= x[0] <= max(x1, x2) and min(y1, y2) <= 10**8-x[1] <= max(y1, y2)]
	#print(f)
	else:
		f = [[x[0], x[1], i] for i, x in enumerate(f) if min(x1, x2) <= x[0] <= max(x1, x2) and min(y1, y2) <= x[1] <= max(y1, y2)]
	n = len(f)
	if n == 0:
		print((abs(x1-x2) + abs(y1-y2)) * 100)
		sys.exit()
	f.sort(key=lambda x: x[1])
	f = [[x[0], i] for i, x in enumerate(f)]
	f.sort(reverse=True)
	st = Rmax(n)
	for x, y in f:
		st.Update(y, st.Get(y, n) + 1)
	num = st.Get(0, n)
	ans = (abs(x1-x2) + abs(y1-y2)) * 100 + num * 5 * pi - num * 20
	if num == min(abs(x1-x2), abs(y1-y2)) + 1:
		ans += 5 * pi
	print("{0:.15f}".format(ans))
