import sys
sys.setrecursionlimit(1000000)

def dfs(x, order, edges, visited):
  visited[x] = True
  for e in edges[x]:
    if not visited[e]:
      dfs(e, order, edges, visited)
  order.append(x)

def dfs_rev(x, rev_edges, visited):
  visited[x] = True
  ret = 0
  for e in rev_edges[x]:
    if not visited[e]:
      ret += 1
      ret += dfs_rev(e, rev_edges, visited)
  return ret


n = int(input())
alst = list(map(int, input().split()))
edges = [[] for _ in range(n)]
rev_edges = [[] for _ in range(n)]
for i in range(n):
  edges[i].append((i + alst[i]) % n)
  rev_edges[(i + alst[i]) % n].append(i)

order = []
visited = [False] * n
for i in range(n):
  if not visited[i]:
    dfs(i, order, edges, visited)
order.reverse()

visited = [False] * n
cycles = 0
for i in order:
  if not visited[i]:
    a = dfs_rev(i, rev_edges, visited)
    if a > 0 or i in rev_edges[i]:
      a += 1
    cycles += a
print(cycles)
