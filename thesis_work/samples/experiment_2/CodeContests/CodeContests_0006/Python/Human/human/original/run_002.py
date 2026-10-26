import io
import os

from collections import Counter, defaultdict, deque, namedtuple

# From: https://github.com/cheran-senthil/PyRival/blob/master/pyrival/data_structures/SegmentTree.py
class SegmentTree:
    def __init__(self, data, default=0, func=max):
        """initialize the segment tree with data"""
        self._default = default
        self._func = func
        self._len = len(data)
        self._size = _size = 1 << (self._len - 1).bit_length()

        self.data = [default] * (2 * _size)
        self.data[_size : _size + self._len] = data
        for i in reversed(range(_size)):
            self.data[i] = func(self.data[i + i], self.data[i + i + 1])

    def __delitem__(self, idx):
        self[idx] = self._default

    def __getitem__(self, idx):
        return self.data[idx + self._size]

    def __setitem__(self, idx, value):
        idx += self._size
        self.data[idx] = value
        idx >>= 1
        while idx:
            self.data[idx] = self._func(self.data[2 * idx], self.data[2 * idx + 1])
            idx >>= 1

    def __len__(self):
        return self._len

    def query(self, start, stop):
        """func of data[start, stop)"""
        start += self._size
        stop += self._size

        res = self._default
        while start < stop:
            if start & 1:
                res = self._func(res, self.data[start])
                start += 1
            if stop & 1:
                stop -= 1
                res = self._func(res, self.data[stop])
            start >>= 1
            stop >>= 1
        return res

    def __repr__(self):
        return "SegmentTree({0})".format(self.data)


def solve(N, X, V):
    # Sort by x coordinate first
    indices = sorted(range(N), key=lambda i: X[i])
    X = [X[i] for i in indices]
    V = [V[i] for i in indices]

    # Calculate where each index should be mapped to if sorted by velocity instead
    BIL = 10 ** 9
    vIndices = sorted(range(N), key=lambda i: V[i] * BIL + X[i])
    indicesByV = {
        originalIndex: sortedIndex for sortedIndex, originalIndex in enumerate(vIndices)
    }
    # Create a segment tree with placeholders for where the velocities should be set
    segTreeTotal = SegmentTree([0 for i in range(N)], 0, lambda a, b: a + b)
    segTreeCount = SegmentTree([0 for i in range(N)], 0, lambda a, b: a + b)
    ans = 0
    for j in range(N):
        # Calculate sum X[j]-X[i] such that V[i] <= V[j], i < j
        index = indicesByV[j]
        count = segTreeCount.query(0, index)
        totalXi = segTreeTotal.query(0, index)
        # print(j, X[j], count, totalXi)
        ans += X[j] * count - totalXi
        segTreeCount[index] = 1
        segTreeTotal[index] = X[j]
    return ans


def solveBrute(N, X, V):
    # Sort by x coordinate first (x is distinct)
    indices = sorted(range(N), key=lambda i: X[i])
    X = [X[i] for i in indices]
    V = [V[i] for i in indices]

    ans = 0
    for j in range(N):
        xj = X[j]
        vj = V[j]
        total = 0
        totalXi = 0
        count = 0
        for i in range(j):
            xi = X[i]
            vi = V[i]
            assert xi <= xj
            if vi <= vj:
                total += xj - xi
                totalXi += xi
                count += 1
        print(j, X[j], count, totalXi)
        assert total == X[j] * count - totalXi
        ans += total

    return ans


if False:
    import random

    random.seed(0)
    N = 200000
    for i in range(1):
        X = [random.randint(0, 10) for i in range(N)]
        V = [random.randint(0, 10) for i in range(N)]
        ans = solve(N, X, V)
        # assert solveBrute(N, X, V) == ans


if __name__ == "__main__":
    input = io.BytesIO(os.read(0, os.fstat(0).st_size)).readline

    (N,) = [int(x) for x in input().split()]
    X = [int(x) for x in input().split()]
    V = [int(x) for x in input().split()]
    ans = solve(N, X, V)
    print(ans)
