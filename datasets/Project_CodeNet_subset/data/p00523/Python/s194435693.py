def d(p, q):
    return pos[p] - pos[q]

def solve():
    p0, p1, p2 = 0, 0, 0
    best = 0
    while p0 < N:
        # p1????±???????
        if p1 <= p0: p1 = p0 + 1
        while d(p1, p0) <= best and p1 - p0 < N:
            p1 += 1
        if d(p1, p0) <= best:
            p0 += 1
            continue
        # p2????±???????
        while d(p2, p1) > d(p1, p0) and p2 > p1:
            p2 -= 1
        if p2 <= p1: p2 = p1 + 1
        while d(p2,p1) < d(p1, p0) and p2 - p0 < N:
            p2 += 1
        if d(p2, p1) < d(p1, p0):
            p0 += 1
            continue
        # check
        if L - d(p2, p0) >= d(p1, p0):
            best = d(p1, p0)
            if best >= L//3:
                return best
        else:
            p0 += 1
    return best

N = int(input())
A = [0] * N
L = 0
for i in range(N):
    A[i] = int(input())
    L += A[i]
pos = [0] * (2*N)
for i in range(1, 2*N):
    pos[i] = pos[i-1] + A[(i-1)%N]

print(solve())