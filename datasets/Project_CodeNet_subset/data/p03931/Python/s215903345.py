n, m = map(int, raw_input().split())
a = map(int, raw_input().split())
dp = [0] * 256 * 101
dp[0] = 1
for i, x in enumerate(a):
    ndp = dp[:]
    for j in xrange(256):
        for k in xrange(i + 1):
            ndp[(j^x)+k*256+256] += dp[j+k*256]
            if ndp[(j^x)+k*256+256] >= 1000000007:
                ndp[(j^x)+k*256+256] -= 1000000007
    dp = ndp
ans = 0
s = 1
for i in xrange(1, n + 1):
    s = s * i % 1000000007
    ans += dp[m+i*256] * s % 1000000007
print ans % 1000000007
