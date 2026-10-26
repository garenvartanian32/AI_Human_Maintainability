import sys

def main():
    data = sys.stdin.read().split()
    idx = 0
    n, m = int(data[idx]), int(data[idx+1]); idx += 2
    students = []
    for _ in range(n):
        a, b = int(data[idx]), int(data[idx+1]); idx += 2
        students.append((a,b))
    checks = []
    for _ in range(m):
        c, d = int(data[idx]), int(data[idx+1]); idx += 2
        checks.append((c,d))
    out = []
    for a, b in students:
        best_j = 1
        best_d = None
        for j, (c, d) in enumerate(checks, 1):
            dist = abs(a-c) + abs(b-d)
            if best_d is None or dist < best_d:
                best_d = dist
                best_j = j
        out.append(str(best_j))
    print('\n'.join(out))

main()