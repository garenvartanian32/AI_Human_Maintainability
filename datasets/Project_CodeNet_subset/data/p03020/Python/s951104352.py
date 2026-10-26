import sys
from collections import deque

input = sys.stdin.readline

N = int(input())
N_ = 2 * N + 6
N1, N2, N3, N4, N5 = range(N + 1, N + 6)
G = [[] for i in range(2 * N + 6)]
INF = 10 ** 12

def add_edge(fr, to, cap, cost) :
        G[fr].append([to, cap, cost, len(G[to])])
        G[to].append([fr, 0, -cost, len(G[fr]) - 1])

def flow(s, t, f) :
    ret = 0
    pre_v = [0] * N_
    pre_e = [0] * N_

    while f :
        dist = [INF] * N_
        dist[s] = 0
        que = deque([s])
        updated = [False] * N_
        updated[s] = True

        while que :
            v = que.popleft()
            if not updated[v] :
                continue

            updated[v] = False

            for i, (w, cap, cost, _) in enumerate(G[v]) :
                if cap > 0 and dist[w] > dist[v] + cost:
                    dist[w] = dist[v] + cost
                    pre_v[w], pre_e[w] = v, i
                    que.append(w)
                    updated[w] = True

        d, v = f, t
        while v != s :
            d = min(d, G[pre_v[v]][pre_e[v]][1])
            v = pre_v[v]

        f -= d
        ret += d * dist[t]

        v = t
        while v != s :
            e = G[pre_v[v]][pre_e[v]]
            e[1] -= d
            G[v][e[3]][1] += d
            v = pre_v[v]
    
    return ret

S = 0
for i in range(1, N + 1) :
    Rx, Ry, Rc = map(int, input().split())

    add_edge(0, i, Rc, 0)

    add_edge(i, N1, INF, -Rx - Ry)
    add_edge(i, N2, INF, Rx - Ry)
    add_edge(i, N3, INF, -Rx + Ry)
    add_edge(i, N4, INF, Rx + Ry)
    S += Rc

for i in range(N5, N_ - 1) :
    Bx, By, Bc = map(int, input().split())
    add_edge(N1, i, INF, Bx + By)
    add_edge(N2, i, INF, -Bx + By)
    add_edge(N3, i, INF, Bx - By)
    add_edge(N4, i, INF, -Bx - By)

    add_edge(i, N_ - 1, Bc, 0)

print(-flow(0, N_ - 1, S))
