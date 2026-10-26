import sys

def main():
    data = sys.stdin.buffer.read().split()
    idx = 0
    n = int(data[idx]); k = int(data[idx+1]); idx += 2
    dual = []
    only_a = []
    only_b = []
    for _ in range(n):
        t = int(data[idx]); a = int(data[idx+1]); b = int(data[idx+2])
        idx += 3
        if a == 1 and b == 1:
            dual.append(t)
        elif a == 1:
            only_a.append(t)
        elif b == 1:
            only_b.append(t)
    dual.sort()
    only_a.sort()
    only_b.sort()
    pre_dual = [0] * (len(dual) + 1)
    for i, t in enumerate(dual):
        pre_dual[i + 1] = pre_dual[i] + t
    pre_a = [0] * (len(only_a) + 1)
    for i, t in enumerate(only_a):
        pre_a[i + 1] = pre_a[i] + t
    pre_b = [0] * (len(only_b) + 1)
    for i, t in enumerate(only_b):
        pre_b[i + 1] = pre_b[i] + t

    best = -1
    for j in range(0, len(dual) + 1):
        need = max(k - j, 0)
        if need > len(only_a) or need > len(only_b):
            continue
        cost = pre_dual[j] + pre_a[need] + pre_b[need]
        if best == -1 or cost < best:
            best = cost
    print(best)

main()