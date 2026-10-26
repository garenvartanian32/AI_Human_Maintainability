import sys

def main():
    data = sys.stdin.read().split()
    idx = 0
    t = int(data[idx]); idx += 1
    out = []
    for _ in range(t):
        k = int(data[idx]); n = int(data[idx+1]); m = int(data[idx+2]); idx += 3
        a = data[idx:idx+n]; idx += n
        b = data[idx:idx+m]; idx += m
        a = list(map(int, a))
        b = list(map(int, b))
        length = k
        i = j = 0
        res = []
        ok = True
        while i < n or j < m:
            if i < n and (a[i] == 0 or a[i] <= length):
                if a[i] == 0:
                    length += 1
                res.append(a[i])
                i += 1
            elif j < m and (b[j] == 0 or b[j] <= length):
                if b[j] == 0:
                    length += 1
                res.append(b[j])
                j += 1
            else:
                ok = False
                break
        if ok:
            out.append(' '.join(map(str, res)))
        else:
            out.append('-1')
    print('\n'.join(out))

main()