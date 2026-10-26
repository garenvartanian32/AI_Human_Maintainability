def dist(pos):
    r = []
    for i in xrange(len(cave)):
        r.append(((cave[i][0]**2 + cave[i][1]**2), cave[i]))
    r.sort(reverse = True)
    return r[0][1]

n = int(raw_input())
for i in xrange(n):
    pos = [0,0]
    cave = []
    while 1:
        x,y = map(int, raw_input().split())
        if x == 0 and y == 0:
            fpos = dist(cave)
            print "%d %d" % (fpos[0], fpos[1])
            break
        pos[0] += x
        pos[1] += y
        cave.append([pos[0], pos[1]])