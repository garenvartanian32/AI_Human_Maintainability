import math

def main():
    x1, y1, x2, y2 = map(int, input().split())
    n = int(input())
    fountains = []
    for _ in range(n):
        X, Y = map(int, input().split())
        fountains.append((X, Y))
    fset = set(fountains)
    base = abs(x1-x2)*100 + abs(y1-y2)*100
    corner1 = (x1, y2)
    corner2 = (x2, y1)
    if corner1 not in fset and corner2 not in fset:
        print(float(base))
        return
    extra = (math.pi/2 - 2) * 10
    print(base + extra)

main()