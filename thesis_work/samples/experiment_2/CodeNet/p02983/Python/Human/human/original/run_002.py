L, R = map(int, input().split())
if R - L >= 2019:
  ans = 0
elif L % 2019 > R % 2019:
  ans = 0
else:
  ans = 2019
  for i in range(L % 2019, R % 2019):
    for j in range(i + 1, R % 2019 + 1):
      ans = min(ans, (i * j) % 2019)
      if ans == 0:
        break
    if ans == 0:
       break
print(ans)