n, k = map(int, input().split())
*A, = map(int, input().split())

MOD = 10**9 + 7

C = {a for a in A}
n = len(C)
m = [[0]*256 for i in range(n+1)]
m[0][0] = 1
for c in C:
    for i in range(n-1, -1, -1):
        s = m[i]; t = m[i+1]
        for j in range(256):
            t[c^j] += s[j]

ans = 0
r = 1
for i in range(1, n+1):
    r = (r * i) % MOD
    ans += r * m[i][k]
    ans %= MOD
print(ans)