import sys
from collections import deque

def main():
    data = sys.stdin.read().split()
    idx = 0
    x0, y0, x1, y1, n = int(data[idx]), int(data[idx+1]), int(data[idx+2]), int(data[idx+3]), int(data[idx+4])
    idx += 5
    segs = []
    for _ in range(n):
        r, a, b = int(data[idx]), int(data[idx+1]), int(data[idx+2]); idx += 3
        segs.append((r,a,b))
    allowed = set()
    rows_of_interest = set()
    for r,a,b in segs:
        allowed.add((r,a,b))
    def in_allowed(x, y):
        for r,a,b in segs:
            if r == x and a <= y <= b:
                return True
        return False
    # coordinate compress candidate cells: endpoints and neighbors, plus start/end
    xs = set()
    for r,a,b in segs:
        for rr in (r-1,r,r+1):
            xs.add(rr)
    xs.add(x0); xs.add(x1)
    cells = set()
    for r,a,b in segs:
        for cc in (a-1,a,a+1,b-1,b,b+1):
            cells.add((r,cc))
    cells.add((x0,y0)); cells.add((x1,y1))
    # filter to only allowed cells among candidates, plus build quick lookup by row
    from collections import defaultdict
    row_segs = defaultdict(list)
    for r,a,b in segs:
        row_segs[r].append((a,b))
    def is_allowed(x,y):
        for a,b in row_segs.get(x, []):
            if a <= y <= b:
                return True
        return False
    valid_cells = set(c for c in cells if is_allowed(c[0], c[1]))
    if (x0,y0) not in valid_cells or (x1,y1) not in valid_cells:
        print(-1)
        return
    dist = {(x0,y0): 0}
    q = deque([(x0,y0)])
    while q:
        cx, cy = q.popleft()
        if (cx,cy) == (x1,y1):
            print(dist[(cx,cy)])
            return
        for dx in (-1,0,1):
            for dy in (-1,0,1):
                if dx == 0 and dy == 0:
                    continue
                nx, ny = cx+dx, cy+dy
                if (nx,ny) in valid_cells and (nx,ny) not in dist:
                    dist[(nx,ny)] = dist[(cx,cy)]+1
                    q.append((nx,ny))
    print(-1)

main()