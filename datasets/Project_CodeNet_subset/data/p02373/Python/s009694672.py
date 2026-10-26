from sys import stdin
from collections import defaultdict
readline = stdin.readline
 
 
def main():
    n = int(readline())
    g = dict()
    for i in range(n):
        nodes = list(map(int, readline().split()))
        g[i] = nodes[1:]
    
    euler, height = euler_tour(g, n)
    index = defaultdict(list)
    for i in range(len(euler)):
        index[euler[i]].append(i)
    rmql = [(height[i], i) for i in euler]
    rmq = segment_tree(rmql,min,(float('inf'),))
 
    q = int(readline())
    for i in range(q):
        u, v = map(int, readline().split())
        l, r = index[u][0], index[v][-1]
        if l > r:
            l, r = index[v][0], index[u][-1]
        print(rmq.find(l, r)[1])
 
 
def euler_tour(g, size):
    height = [None] * size
    euler = []
    root = 0
    dfs_stack = [(root, None, 0)]
    while dfs_stack:
        u, prev, h = dfs_stack.pop()
        height[u] = h
        euler.append(prev)
        if g[u]:
            dfs_stack.extend((v, u, h + 1) for v in g[u])
        else:
            euler.append(u)
    return euler[1:], height
     
import math
class segment_tree:
    def __init__(self, dat, query, default=0):
        self.offset = 2 ** math.ceil(math.log(len(dat), 2))
        self.table = [default] * self.offset + dat + [default] * (self.offset - len(dat))
        self.query = query
        for i in reversed(range(1, self.offset)):
            self.table[i] = self.query(self.table[2 * i], self.table[2 * i + 1])
 
    # [l, r] closed-interval
    def find(self, l, r):
        return self.query(self.__range(l,r))
 
    def __range(self, l, r):
        l += self.offset
        r += self.offset
        while l <= r:
            if l & 1:
                yield self.table[l]
                l += 1
            l >>= 1
            if r & 1 == 0:
                yield self.table[r]
                r -= 1
            r >>= 1
         
    def update(self, i, x):
        i += self.offset
        self.table[i] = x
        while 1 < i:
            i >>= 1
            self.table[i] = self.query(self.table[2 * i], self.table[2 * i + 1])
main()