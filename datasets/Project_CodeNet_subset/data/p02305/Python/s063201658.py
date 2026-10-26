from math import sqrt
from typing import Tuple


def dist(p: Tuple[float, float], q: Tuple[float, float]) -> float:
    return sqrt((p[0] - q[0]) ** 2 + (p[1] - q[1]) ** 2)


if __name__ == "__main__":
    c1x, c1y, c1r = map(lambda x: float(x), input().split())
    c2x, c2y, c2r = map(lambda x: float(x), input().split())
    if c2r < c1r:
        c1x, c1y, c1r, c2x, c2y, c2r = c2x, c2y, c2r, c1x, c1y, c1r

    center_dist = dist((c1x, c1y), (c2x, c2y))
    if (c1r + c2r) < center_dist:
        print(4)
    elif (c1r + c2r) == center_dist:
        print(3)
    elif center_dist < (c1r + c2r) and (c2r - c1r) < center_dist:
        print(2)
    elif center_dist == (c2r - c1r):
        print(1)
    else:
        print(0)

