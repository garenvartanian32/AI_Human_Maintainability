#!python3

from collections import defaultdict

iim = lambda: map(int, input().rstrip().split())

def resolve():
    N, V = iim()
    AN = 4
    A = [list(iim()) for i in range(AN)]

    def dd(a1, a2):
        d = defaultdict(int)
        for i in a1:
            for j in a2:
                x = i + j
                if x < V:
                    d[x] += 1
        return d

    da, db = dd(A[0], A[1]), dd(A[2], A[3])

    print(sum(v * db[V-k] for k, v in da.items()))

if __name__ == "__main__":
    resolve()

