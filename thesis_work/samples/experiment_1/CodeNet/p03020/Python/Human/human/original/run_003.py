import sys
from collections import deque


def min_cost_flow(links, links_from, s, t, flow, n2):
    remain = flow
    result = 0
    INF = 10 ** 12
    predecessors = [0] * n2  # link_id

    while remain:
        # print(remain)
        distances = [INF] * n2
        updated = [False] * n2
        distances[s] = 0
        updated[s] = True
        q = deque([s])
        while q:
            v = q.popleft()
            vc = distances[v]
            updated[v] = False

            for li in links_from[v]:
                _, u, cap, cost = links[li]
                if cap == 0:
                    continue
                new_cost = vc + cost
                if new_cost >= distances[u]:
                    continue
                distances[u] = new_cost
                predecessors[u] = li
                if not updated[u]:
                    updated[u] = True
                    q.append(u)

        min_cap = remain
        v = t
        while v != s:
            li = predecessors[v]
            l = links[li]
            min_cap = min(min_cap, l[2])
            v = l[0]
        v = t
        while v != s:
            li = predecessors[v]
            l = links[li]
            l[2] -= min_cap
            links[li ^ 1][2] += min_cap
            v = l[0]
        remain -= min_cap
        result -= min_cap * distances[t]
    return result


n = int(input())
lines = sys.stdin.readlines()
n2 = 2 * n + 6
s, t = 0, 2 * n + 1
k1, k2, k3, k4 = range(n2 - 4, n2)
balls = 0
links_from = [[] for _ in range(n2)]
links = []  # [[src, tgt, capacity, unit_cost], ]


def add_link(s, t, cap, cost):
    i = len(links)
    links.append([s, t, cap, cost])
    links.append([t, s, 0, -cost])
    links_from[s].append(i)
    links_from[t].append(i + 1)


for i in range(n):
    ri = i + 1
    rx, ry, rc = map(int, lines[i].split())
    balls += rc
    add_link(s, ri, rc, 0)
    add_link(ri, k1, rc, rx + ry)
    add_link(ri, k2, rc, -rx + ry)
    add_link(ri, k3, rc, rx - ry)
    add_link(ri, k4, rc, -rx - ry)

for i in range(n, 2 * n):
    bi = i + 1
    bx, by, bc = map(int, lines[i].split())
    add_link(bi, t, bc, 0)
    add_link(k1, bi, bc, -bx - by)
    add_link(k2, bi, bc, bx - by)
    add_link(k3, bi, bc, -bx + by)
    add_link(k4, bi, bc, bx + by)

print(min_cost_flow(links, links_from, s, t, balls, n2))
