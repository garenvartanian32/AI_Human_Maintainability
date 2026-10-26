import sys
from collections import defaultdict

if __name__ == '__main__':
    n, v = map(int, sys.stdin.readline().split())
    a = list(map(int, sys.stdin.readline().split()))
    b = list(map(int, sys.stdin.readline().split()))
    c = list(map(int, sys.stdin.readline().split()))
    d = list(map(int, sys.stdin.readline().split()))
    mp = defaultdict(int)
    ls = []
    for val1 in c:
        for val2 in d:
            mp[val1+val2] += 1
    ans = 0
    for val1 in a:
        for val2 in b:
           ans += mp[v-val1-val2]
    print(ans)

