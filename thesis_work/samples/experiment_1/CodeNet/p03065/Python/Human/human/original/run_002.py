def e_polynominal_divisors(N, A):
    # 参考: http://drken1215.hatenablog.com/entry/2019/04/21/004100
    from fractions import gcd

    def prime_factorization_dict(n):
        """nを素因数分解したときの素数とその指数の辞書"""
        i, table = 2, {}
        while i**2 <= n:
            table[i] = 0
            while n % i == 0:
                table[i] += 1
                n //= i
            if table[i] == 0:
                table.pop(i)
            i += 1
        if n > 1:
            table[n] = 1
        return table

    def prime_table(n):
        """n以下の素数のリスト(エラトステネスの篩)"""
        if n == 0:
            return []
        is_prime = [True] * (n + 1)
        is_prime[0] = is_prime[1] = False  # 0と1は素数でない
        for j in range(2, int(n**0.5) + 1):
            if not is_prime[j]:
                continue
            for k in range(j * 2, n + 1, j):
                is_prime[k] = False  # jの倍数は素数でない
        return [i for i in range(n + 1) if is_prime[i]]

    # f(x) % p == 0 のとき、f(x)の係数はすべてpの倍数。よって、f(x)の各係数の
    # GCDを求め、それを素因数分解して得られる素数が解である。
    # ただし、係数が0の項は考慮しない。
    g = abs(A[N])
    for a in A:
        if a != 0:
            g = gcd(g, abs(a))
    ans = list(prime_factorization_dict(g).keys())

    a = A[::-1]  # Aは係数が最高次から格納されているが、それを最低次からにする
    # p<=N である素数pについて、f(x)がx**p-xで割れるか？
    # それには、f(x)≡h(x) (mod p) を満たすようなp-1次以下の多項式h(x)について、
    # h(x)≡0 (mod p) ⇔ h(x)の係数がすべてpの倍数である
    # を満たすか判定する
    for p in prime_table(N):
        c = [0] * p
        for d in range(p):
            c[d] = a[d] % p
        # x**p≡x (mod p) なので、インデックスはp-1の周期でループする
        for d in range(p, N + 1):
            r = d % (p - 1)
            if r == 0:
                r = p - 1
            c[r] = (c[r] + a[d]) % p

        for factor in c:
            if factor != 0:
                break
        else:
            # h(x) の係数がすべてpの倍数である。f(x)はx**p-xで割りきれる
            ans.append(p)
    ans = sorted(set(ans))
    return '\n'.join(map(str, ans))

N = int(input())
A = [int(input()) for _ in range(N + 1)]
print(e_polynominal_divisors(N, A))