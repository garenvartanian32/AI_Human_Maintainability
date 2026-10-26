# Testing
# https://codeforces.com/contest/1136/submission/51182491
# with super quick input
import os

def read_input():
    s = os.read(0,os.fstat(0).st_size)
    inp = []
    numb = 0
    for i in range(len(s)):
        if s[i]>=48:
            numb = 10*numb + s[i]-48
        elif s[i]!=13: #\r
            inp.append(numb)
            numb = 0
    if s[-1]>=48:
        inp.append(numb)
    return inp

inp = read_input()
ii = 0

n, m = inp[ii],inp[ii+1]
ii+=2

a = inp[ii:ii+n]
ii+=n
last = a.pop()

friend = [[] for i in range(n + 1)]
for _ in range(m):
    u, v = inp[ii],inp[ii+1]
    ii+=2
    friend[u].append(v)

mark = [0] * (n + 1)
mark[last] = 1
req = 1
ans = 0
for i in reversed(range(n - 1)):
    u = a[i]
    cnt = sum(mark[v] for v in friend[u])
    if cnt == req:
        ans += 1
    else:
        mark[u] = 1
        req += 1
print(ans)