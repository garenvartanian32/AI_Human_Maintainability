import sys

def main():
    data = sys.stdin.read().split()
    idx = 0
    q = int(data[idx]); idx += 1
    out = []
    for _ in range(q):
        l = int(data[idx]); r = int(data[idx+1]); d = int(data[idx+2])
        idx += 3
        if d < l or d > r:
            out.append(str(d))
        else:
            out.append(str(((r // d) + 1) * d))
    print('\n'.join(out))

main()