x1, y1, r1 = map(int, raw_input().split())
x2, y2, r2 = map(int, raw_input().split())
rr = (x1 - x2)**2 + (y1 - y2)**2
ro = (r1 + r2)**2
ri = (r1 - r2)**2
if rr > ro:
    print 4
elif rr == ro:
    print 3
elif ri < rr < ro:
    print 2
elif ri == rr:
    print 1
else: # rr < ri
    print 0