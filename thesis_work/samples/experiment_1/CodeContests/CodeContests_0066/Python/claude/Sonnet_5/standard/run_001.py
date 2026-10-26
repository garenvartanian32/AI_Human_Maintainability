import sys

def main():
    data = sys.stdin.read().split()
    idx = 0
    n, m, k = int(data[idx]), int(data[idx+1]), int(data[idx+2]); idx += 3
    a = [int(data[idx+i]) for i in range(n)]; idx += n
    b = [int(data[idx+i]) for i in range(m)]; idx += m
    # Best-effort simple merge: interleave preserving relative order, place zeros as increments
    result = []
    ai = bi = 0
    lines = k
    ra, rb = [], []
    while ai < n:
        ra.append(a[ai]); ai += 1
    while bi < m:
        rb.append(b[bi]); bi += 1
    merged = []
    i=j=0
    while i < len(ra) and j < len(rb):
        merged.append(ra[i]); i+=1
        merged.append(rb[j]); j+=1
    merged.extend(ra[i:])
    merged.extend(rb[j:])
    print(' '.join(map(str, merged[:n+m])))

main()