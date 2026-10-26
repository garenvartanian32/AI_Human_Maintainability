import sys

def main():
    data = sys.stdin.read().split()
    idx = 0
    n = int(data[idx]); m = int(data[idx+1]); idx += 2
    stores = []
    for _ in range(n):
        a = int(data[idx]); b = int(data[idx+1]); idx += 2
        stores.append((a, b))
    stores.sort()
    total_cost = 0
    remaining = m
    for a, b in stores:
        take = min(b, remaining)
        total_cost += take * a
        remaining -= take
        if remaining == 0:
            break
    print(total_cost)

main()