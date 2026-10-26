from sys import stdin, gettrace
from heapq import nlargest

if not gettrace():
    def input():
        return next(stdin)[:-1]


# def input():
#    return stdin.buffer.readline()
INF = int(10E10)

def main():
    def solve():

        n, k = map(int, input().split())
        mm = []
        for i in range(1,n+1):
            a,b = map(int, input().split())
            mm.append((b, a, i))
        mm.sort()
        dp = [[(-1000000, False)] * min(i+1,k+1) for i in range(n+1)]
        dp[1][0] = (mm[0][0] * (k-1), False)
        dp[1][1] = (mm[0][1], True)
        for j,(b,a,_) in enumerate(mm[1:],2):
            dp[j][0] = (dp[j-1][0][0] + b * (k-1), False)
            for l in range(1, min(j, k+1)):
                v1 = dp[j-1][l][0] + b * (k-1)
                v2 = dp[j-1][l-1][0] + a + b * (l-1)
                if v1 > v2:
                    dp[j][l] = (v1, False)
                else:
                    dp[j][l] = (v2, True)
            if j <= k:
                dp[j][j] = (dp[j-1][j-1][0] + a + b * (j-1), True)
        g1 = []
        g2 = []
        l = k
        for j in range(n, 0, -1):
            _, _, i = mm[j-1]
            if dp[j][l][1]:
                g1.append(i)
                l -= 1
            else:
                g2.append(i)
                g2.append(-i)
        g1.reverse()
        res = g1[:k-1] + g2 + g1[k-1:]
        print(len(res))
        print(' '.join(map(str, res)))

    q = int(input())
    for _ in range(q):
        solve()


if __name__ == "__main__":
    main()