import sys

def main():
    data = sys.stdin.read().split()
    idx = 0
    n = int(data[idx]); k = int(data[idx+1]); idx += 2
    both = []
    onlyA = []
    onlyB = []
    for _ in range(n):
        t = int(data[idx]); a = int(data[idx+1]); b = int(data[idx+2]); idx += 3
        if a and b:
            both.append(t)
        elif a:
            onlyA.append(t)
        elif b:
            onlyB.append(t)
    both.sort(); onlyA.sort(); onlyB.sort()
    prefBoth = [0]*(len(both)+1)
    for i, v in enumerate(both):
        prefBoth[i+1] = prefBoth[i]+v
    prefA = [0]*(len(onlyA)+1)
    for i, v in enumerate(onlyA):
        prefA[i+1] = prefA[i]+v
    prefB = [0]*(len(onlyB)+1)
    for i, v in enumerate(onlyB):
        prefB[i+1] = prefB[i]+v
    best = None
    for x in range(0, min(len(both), k)+1):
        needA = max(0, k-x)
        needB = max(0, k-x)
        if needA > len(onlyA) or needB > len(onlyB):
            continue
        total = prefBoth[x] + prefA[needA] + prefB[needB]
        if best is None or total < best:
            best = total
    if best is None:
        print(-1)
    else:
        print(best)

main()