import sys
input = sys.stdin.buffer.readline

n = int(input())
li = [list(map(int, input().split()))[1:] for _ in range(n)]


def prepare(n: int, con: list):
	sz = n.bit_length()
	parent = [[0] * n for _ in range(sz)]
	parent[0][0] = -1
	depth = [-1] * n
	depth[0] = 0
	q = [0]
	while q:
		cur = q.pop()
		for nxt in con[cur]:
			if depth[nxt] < 0:
				depth[nxt] = depth[cur] + 1
				parent[0][nxt] = cur
				q.append(nxt)
	for k in range(sz - 1):
		for i in range(n):
			if parent[k][i] < 0:
				parent[k + 1][i] = -1
			else:
				parent[k + 1][i] = parent[k][parent[k][i]]
	return sz, depth, parent


sz, depth, parent = prepare(n, li)


def lca(u, v):
	if depth[u] > depth[v]:
		u, v = v, u
	for k in range(sz):
		if ((depth[v] - depth[u]) >> k & 1):
			v = parent[k][v]
	if u == v:
		return u
	for k in range(sz - 1, -1, -1):
		if parent[k][u] != parent[k][v]:
			u, v = parent[k][u], parent[k][v]
	return parent[0][u]


ans = []
for _ in range(int(input())):
	u, v = map(int, input().split())
	ans.append(lca(u, v))
print("\n".join(map(str, ans)))

