import sys
input = sys.stdin.readline

def main():
    N = int(input())
    A = list(map(int, input().split()))

    mod = 998244353

    max_l = 0
    Dig = [0]*11
    for a in A:
        l = len(str(a))
        Dig[l-1] += 1
        max_l = max(max_l, l)

    D = [0]*21
    for a in A:
        l = len(str(a))
        for j in range(max_l):
            for k in range(l):
                n = int(str(a)[l-1-k])
                ck = Dig[j]
                if j >= k:
                    D[k*2] += ck*n % mod
                    D[k*2+1] += ck*n % mod
                else:
                    D[j+k+1] += 2*ck*n % mod

    ans = 0
    for i in range(21):
        ans = (ans + (D[i]%mod)*(10**i%mod)) % mod
    print(ans)


if __name__ == "__main__":
    main()