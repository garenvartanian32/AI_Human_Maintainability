n, x = list(map(int, input().split()))
B = 0
blu = []
s = [0 for i in range(n)]

for i in range(n):
  b, l, u = list(map(int, input().split()))
  blu.append((b, l, u, x*u-b*u+b*l))
  B += b*l
blu.sort(key=lambda x: x[3], reverse=True)

for i in range(n):
  if i==0:
    s[i] = blu[i][3]
  else:
    s[i] = s[i-1] + blu[i][3]

ok = n*x
ng = -1
while ok-ng>1:
  A = 0
  mid = (ok+ng)//2
  k = mid//x
  t = mid%x
  for i in range(n):
    if t<=blu[i][0]:
      rm = t*blu[i][1]
    else:
      rm = t*blu[i][2] - blu[i][0]*(blu[i][2]-blu[i][1])
    if k==0:
      A = max(A, rm)
    elif i<k:
      A = max(A, s[k]-blu[i][3]+rm)
    else:
      A = max(A, s[k-1]+rm)
  if A>=B:
    ok = mid
  else:
    ng = mid

print(ok)