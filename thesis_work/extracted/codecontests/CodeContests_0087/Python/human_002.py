import sys
input = sys.stdin.readline

N = int(2e5+1)
n, k, s, t = map(int, input().split())
car = [list(map(int, input().split())) for _ in range(n)]
g = sorted(list(map(int, input().split()))) + [0]
g[k] = s - g[k - 1]
for i in range(k-1, 0, -1):
    g[i] -= g[i - 1]

def is_valid(g, k, v, t):
    need = 0
    for i in range(k+1):
        need += max(g[i], 3 * g[i] - v)
    return need <= t
    
def binary_search(g, k):
    l, r = max(g), int(2e9)
    while l < r:
        v = l + (r - l) // 2
        if is_valid(g, k, v, t):
            r = v
        else:
            l = v + 1
    return l

l = binary_search(g, k)
res = int(2e9)
for i in range(n):
    if car[i][1] >= l:
      res = min(res, car[i][0])
print(-1 if res == int(2e9) else res)
