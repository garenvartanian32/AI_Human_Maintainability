INF = 10**18

def choice(c):
    for i in range(c):
        for j in range(c):
            if i == j:
                continue
            for k in range(c):
                if (i != k) and (j != k):
                    yield i,j,k

def solve(n, c, D, C):
    P = [{},{},{}]
    for y in range(n):
        for x in range(n):
            i = (x+y) % 3
            k = C[y][x] - 1
            if not k in P[i]:
                P[i][k] = 0
            P[i][k] += 1
    best = INF
    for v in choice(c):
        cur = 0
        for i in range(3):
            t = v[i]
            for s, num in P[i].items():
                cur += D[s][t] * num
        best = min(best, cur)
    return best

n, c = map(int, input().split())
D = [list(map(int, input().split())) for i in range(c)]
C = [list(map(int, input().split())) for i in range(n)]
print(solve(n, c, D, C))