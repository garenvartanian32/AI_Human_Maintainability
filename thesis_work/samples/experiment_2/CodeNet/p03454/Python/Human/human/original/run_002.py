s = int(input())
dv = []
mod = 10**9+7
for i in range(1,int((s+0.5)**0.5)+1):
  if s%i == 0:
    if s//i != i:
      dv.append(s//i)
    dv.append(i)
dv.sort()
ans = 0
cnt = 0
for i in dv:
  if s//i <= 8:
    break
  ans += pow(10,(s//i)-1,mod)*9-i
  ans %= mod
  cnt += 1
ans += (s-1)//8
t = s
for i in range(1,11):
  minus = i*9*10**(i-1)
  if minus > t:
    mx = (t+i-1)//i+10**(i-1)
    break
  else:
    t -= minus
bettable = [[[0,0] for i in range(9)] for j in range(9)]
for i in range(9):
  for j in range(i+1,9):
    for k in range(i+1,j):
      bettable[i][j][0] += k*10**(k-1)*9
      bettable[i][j][1] += 10**(k-1)*9
for i in range((s+7)//8,mx):
  avgl = s//i
  if avgl*i == s and 10**(avgl-1)*9 >= i:
    ans += 10**(avgl-1)*9-i+1
    continue
  avgh = avgl+1
  for l in range(1,avgl+1):
    for h in range(avgh,9):
      t = s-bettable[l][h][0]
      j = i-bettable[l][h][1]
      if t < 0 or j < 0:
        continue
      lnum = min(j,10**(l-1)*9)
      hnum = min(j,10**(h-1)*9)
      if (h*j-t)%(h-l) == 0:
        x = (h*j-t)//(h-l)
        if 0 < x <= lnum and 0 < j-x <= hnum:
          ans += 1
          break
print(ans%mod)