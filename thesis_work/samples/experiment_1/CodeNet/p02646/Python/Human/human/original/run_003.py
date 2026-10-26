A, V = map(int, input().split())
B, W = map(int, input().split())
T = int(input())

if V <= W:
  f = False
else:
  t = abs(B - A) / (V - W)
  if t <= T:
    f = True
  else:
    f = False

print("YES" if f else "NO")