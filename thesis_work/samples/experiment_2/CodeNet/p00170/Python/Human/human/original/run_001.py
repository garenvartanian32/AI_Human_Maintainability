def solve(G1, G2, w1, w2):
  global ans
  n = len(G2)
  for e in G2:
    w, lim, s = Food[e]
    a = w1 + w * n
    if w2 > lim or a > ans[0]: return
    b = G1 + [e]
    if n == 1:
      ans = min(ans, [a, b])
    else:
      x = G2[:]
      x.remove(e)
      solve(b, x, a, w2 + w)
  return
 
while 1:
  n = input()
  if n==0: break
  ans = [1e9, []]
  N = range(n)
  f = lambda x: map(int, x[1:3]) + [x[0]]
  Food = [f(raw_input().split()) for _ in [0]*n]
  solve([], N, 0, 0)
  for e in ans[1][::-1]:
    print Food[e][2]