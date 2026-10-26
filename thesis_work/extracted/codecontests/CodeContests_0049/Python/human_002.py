def b_sort(lst):
  limit = len(lst) - 1
  cnt = 0
  while limit:
    for i in range(limit):
      if lst[i] > lst[i + 1]:
        lst[i], lst[i + 1] = lst[i + 1], lst[i]
        cnt += 1
    limit -= 1
  return cnt

while True:
  n = int(input())
  if n == 0:
    break
  alst = []
  for _ in range(n):
    alst.append(int(input()))
  print(b_sort(alst))
