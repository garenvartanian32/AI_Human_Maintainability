def cross(x, y):
    return (x.conjugate() * y).imag

def is_convex(points):
    n = len(points)
    x = points[1] - points[0]
    y = points[2] - points[1]
    c0 = cross(x, y)
    for i in range(1, n):
        x = y
        y = points[(i+2)%n] - points[(i+1)%n]
        if c0 * cross(x, y) < 0:
            return False
    return True
        

import sys

for line in sys.stdin:
    li = list(map(float, line.split(',')))
    p = []
    for i in range(0, len(li), 2):
        p.append(complex(li[i], li[i+1]))
    if is_convex(p):
        print('YES')
    else:
        print('NO')