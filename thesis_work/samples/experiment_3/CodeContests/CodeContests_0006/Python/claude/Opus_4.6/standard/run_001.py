import sys

def main():
    data = sys.stdin.buffer.read().split()
    idx = 0
    n = int(data[idx]); idx += 1
    xs = [int(data[idx+i]) for i in range(n)]; idx += n
    vs = [int(data[idx+i]) for i in range(n)]; idx += n

    pts = sorted(zip(xs, vs), key=lambda p: p[0])
    sorted_v = sorted(set(vs))
    rank = {v: i + 1 for i, v in enumerate(sorted_v)}
    m = len(sorted_v)
    cnt = [0] * (m + 1)
    sumx = [0] * (m + 1)

    def update(pos, c, s):
        while pos <= m:
            cnt[pos] += c
            sumx[pos] += s
            pos += pos & (-pos)

    def query(pos):
        c = 0
        s = 0
        while pos > 0:
            c += cnt[pos]
            s += sumx[pos]
            pos -= pos & (-pos)
        return c, s

    total = 0
    for x, v in pts:
        r = rank[v]
        c, s = query(r)
        total += c * x - s
        update(r, 1, x)

    print(total)

main()