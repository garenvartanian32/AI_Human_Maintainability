from collections import defaultdict
while True:
  n = int(input())
  if n == 0:
    break
  teams = []
  for _ in range(n):
    i, u, a, p = map(int, input().split())
    teams.append((-a, p, i, u))
  teams.sort()
  
  u_counter = defaultdict(int)
  all_counter = 0
  selected = []
  for _, _, i, u in teams:
    if all_counter < 10 and u_counter[u] < 3 or\
       all_counter < 20 and u_counter[u] < 2 or\
       all_counter < 26 and u_counter[u] < 1:
       u_counter[u] += 1
       all_counter += 1
       selected.append(i)
  
  [print(s) for s in selected]

