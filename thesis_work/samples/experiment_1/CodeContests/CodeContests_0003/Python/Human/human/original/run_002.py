# @author 

import sys

class D1AddOnATree:
    def dfs(self, start):
        self.done[start] = 1
        for x in self.adj[start]:
            if self.done[x]:
                continue
            self.par[x] = start
            self.dfs(x)
    def solve(self):
        from collections import defaultdict
        import sys
        sys.setrecursionlimit(10 ** 5 + 5)
        n = int(input())
        self.adj = defaultdict(list)
        self.par = defaultdict(int)
        self.done = [0] * (n + 1)

        for i in range(n - 1):
            u, v = [int(_) for _ in input().split()]
            self.adj[u].append(v)
            self.adj[v].append(u)
            self.done[u] += 1
            self.done[v] += 1

        for i in range(1, n + 1):
            if self.done[i] == 2:
                print("NO")
                break
        else:
            print("YES")
        return

        v = max(len(self.adj[p]) for p in self.adj)
        start = -1
        for p in self.adj:
            if len(self.adj[p]) == v:
                start = p
                break
        assert(start != -1)

        self.dfs(start)

        cnt = [0] * (n + 1)
        for k in self.adj:
            if self.par[k] == 0:
                continue
            if len(self.adj[k]) == 1:
                cnt[self.par[k]] += 1

        ans = 0
        for x in cnt:
            if x == 1:
                ans += 1

        # for x in adj:
        #     if self.adj

        if ans == 0:
            print("YES")
        else:
            print("NO")


solver = D1AddOnATree()
input = sys.stdin.readline

solver.solve()
