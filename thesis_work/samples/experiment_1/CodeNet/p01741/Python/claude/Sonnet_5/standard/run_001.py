import math

def solve(d):
    best = d * math.sqrt(2)
    dy = 1
    while dy <= d + 1e-12:
        val_sq = d*d - dy*dy
        if val_sq >= -1e-12:
            dx = math.sqrt(max(0, val_sq))
            if dx < 1 - 1e-12:
                best = max(best, dy + 1)
        dy += 1
    return best

def main():
    d = float(input())
    print(f"{solve(d):.12f}")

main()