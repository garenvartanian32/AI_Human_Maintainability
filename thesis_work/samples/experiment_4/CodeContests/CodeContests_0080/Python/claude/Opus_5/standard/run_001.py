import sys

def main():
    data = sys.stdin.read().split('\n')
    a = data[0].strip()
    b = data[1].strip()
    c = data[2].strip()

    def freq(s):
        f = [0]*26
        for ch in s:
            f[ord(ch)-ord('a')] += 1
        return f

    fa = freq(a)
    fb = freq(b)
    fc = freq(c)

    max_x = len(a)
    for i in range(26):
        if fb[i] > 0:
            max_x = min(max_x, fa[i] // fb[i])

    best_total = -1
    best_x = 0
    best_y = 0
    for x in range(max_x + 1):
        remaining = [fa[i] - x*fb[i] for i in range(26)]
        y = len(a)
        feasible = True
        for i in range(26):
            if fc[i] > 0:
                y = min(y, remaining[i] // fc[i])
        total = x + y
        if total > best_total:
            best_total = total
            best_x = x
            best_y = y

    result = []
    result.append(b * best_x)
    result.append(c * best_y)
    used = [0]*26
    for i in range(26):
        used[i] = best_x*fb[i] + best_y*fc[i]
    leftover = []
    for i in range(26):
        cnt = fa[i] - used[i]
        leftover.append(chr(ord('a')+i) * cnt)
    result.append(''.join(leftover))
    print(''.join(result))

main()