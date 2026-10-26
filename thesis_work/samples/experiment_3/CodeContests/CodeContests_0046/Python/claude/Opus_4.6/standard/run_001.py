import sys

def main():
    data = sys.stdin.read().split()
    idx = 0
    n = int(data[idx]); m = int(data[idx+1]); idx += 2
    students = []
    for _ in range(n):
        a = int(data[idx]); b = int(data[idx+1]); idx += 2
        students.append((a, b))
    checkpoints = []
    for _ in range(m):
        c = int(data[idx]); d = int(data[idx+1]); idx += 2
        checkpoints.append((c, d))
    out = []
    for (a, b) in students:
        best_j = -1
        best_dist = None
        for j, (c, d) in enumerate(checkpoints, start=1):
            dist = abs(a - c) + abs(b - d)
            if best_dist is None or dist < best_dist:
                best_dist = dist
                best_j = j
        out.append(str(best_j))
    print('\n'.join(out))

main()