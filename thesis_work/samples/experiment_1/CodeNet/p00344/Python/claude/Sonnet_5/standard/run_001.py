import sys

def main():
    data = sys.stdin.read().split()
    n = int(data[0])
    a = [int(x) for x in data[1:1+n]]
    nxt = [(i + a[i]) % n for i in range(n)]
    state = [0]*n  # 0 unvisited, 1 in progress, 2 done
    on_cycle = [False]*n
    for i in range(n):
        if state[i] != 0:
            continue
        path = []
        cur = i
        while state[cur] == 0:
            state[cur] = 1
            path.append(cur)
            cur = nxt[cur]
        if state[cur] == 1:
            # found a new cycle starting at cur
            idx = path.index(cur)
            for node in path[idx:]:
                on_cycle[node] = True
        for node in path:
            state[node] = 2
    print(sum(on_cycle))

main()