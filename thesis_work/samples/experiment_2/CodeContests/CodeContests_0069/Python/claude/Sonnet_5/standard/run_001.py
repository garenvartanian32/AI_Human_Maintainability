import sys
from collections import deque

def main():
    data = sys.stdin.read().split()
    idx = 0
    x0 = int(data[idx]); y0 = int(data[idx+1]); x1 = int(data[idx+2]); y1 = int(data[idx+3]); idx += 4
    n = int(data[idx]); idx += 1
    cells = {}
    for _ in range(n):
        r = int(data[idx]); a = int(data[idx+1]); b = int(data[idx+2]); idx += 3
        for c in range(a, b + 1):
            cells[(r, c)] = True

    start = (x0, y0)
    end = (x1, y1)
    if start not in cells or end not in cells:
        print(-1)
        return
    dist = {start: 0}
    q = deque([start])
    dirs = [(-1,-1),(-1,0),(-1,1),(0,-1),(0,1),(1,-1),(1,0),(1,1)]
    while q:
        cur = q.popleft()
        if cur == end:
            print(dist[cur])
            return
        cr, cc = cur
        for dr, dc in dirs:
            nxt = (cr+dr, cc+dc)
            if nxt in cells and nxt not in dist:
                dist[nxt] = dist[cur] + 1
                q.append(nxt)
    print(-1)

main()