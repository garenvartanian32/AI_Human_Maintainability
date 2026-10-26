N, M = map(int, input().split())
d = {}
p = {}
INF = 9999999
for i in range(1, N + 1):
  for j in range(1, N + 1):
    if i == j:
      continue
    d[i, j] = INF
    p[i, j] = []
e = set([])
for i in range(M):
  a, b, c = map(int, input().split())
  e.add((a, b))
  d[a, b] = c
  d[b, a] = c
  p[a, b] = [a, b]
  p[b, a] = [b, a]
for k in range(1, N + 1):
  for i in range(1, N + 1):
    if i == k:
      continue
    for j in range(1, N + 1):
      if i == j or j == k:
        continue
      if d[i, j] > d[i, k] + d[k, j]:
        d[i, j] = d[i, k] + d[k, j]
        p[i, j] = p[i, k] + p[k, j][1:]
for i in range(1, N + 1):
  for j in range(1, N + 1):
    if i == j:
      continue
    q = p[i, j]
    for k in range(len(q) - 1):
      r = q[k], q[k + 1]
      s = q[k + 1], q[k]
      if r in e:
        e.remove(r)
      if s in e:
        e.remove(s)
print(len(e))