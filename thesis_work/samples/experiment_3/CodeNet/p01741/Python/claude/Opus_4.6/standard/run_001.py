import sys
import math

def main():
    d = float(sys.stdin.read().strip())
    best = d * math.sqrt(2)
    floor_d = int(math.floor(d + 1e-9))
    for k in range(1, floor_d + 1):
        rem = d*d - k*k
        if rem < 0:
            rem = 0
        dy = math.sqrt(rem)
        val = k + max(1.0, dy)
        if val > best:
            best = val
    print(f"{best:.12f}")

main()