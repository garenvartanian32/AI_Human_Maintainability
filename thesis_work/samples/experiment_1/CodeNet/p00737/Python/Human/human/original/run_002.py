from heapq import heappop, heappush

def main():
  R, D, L, U = 0, 1, 2, 3
  vector = ((1, 0), (0, 1), (-1, 0), (0, -1))
  
  while True:
    w, h = map(int, input().split())
    if w == 0:
      break
    mp = [[5] + list(map(int, input().split())) + [5] for _ in range(h)]
    mp.insert(0, [5] * (w + 2))
    mp.append([5] * (w + 2))
    cost = list(map(int, input().split()))
    que = []
    heappush(que, (0, 1, 1, R))
    dic = {}
    dic[(1, 1, R)] = 0
    while que:
      total, x, y, direct = heappop(que)
      ins = mp[y][x]
      if ins == 5:
        continue
      if ins != 4:
        new_direct = (direct + ins) % 4
        vx, vy = vector[new_direct]
        nx, ny = x + vx, y + vy
        if not (nx, ny, new_direct) in dic or dic[nx, ny, new_direct] > total:
          dic[(nx, ny, new_direct)] = total
          heappush(que, (total, nx, ny, new_direct))
  
      for add_direct in range(4):
        new_direct = (direct + add_direct) % 4
        vx, vy = vector[new_direct]
        nx, ny = x + vx, y + vy
        if not (nx, ny, new_direct) in dic or dic[(nx, ny, new_direct)] > total + cost[add_direct]:
          dic[(nx, ny, new_direct)] = total + cost[add_direct]
          heappush(que, (total + cost[add_direct], nx, ny, new_direct))
    print(min(dic[(w, h, D)], dic[(w, h, R)]))

main()
