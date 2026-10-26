from typing import Optional


class SegmentTree(object):
    def __init__(self, n: int) -> None:
        size = 2 ** (n.bit_length())
        self.size = 2 * size - 1
        self.data = [0] * self.size
        self.lazy = [None] * self.size

    def update(self, lo: int, hi: int, v: int) -> None:
        def _update(r: int, i: int, j: int, lz: Optional[int]) -> int:
            left, right = r * 2 + 1, r * 2 + 2
            if (lz is None):
                lz = lazy[r]
            lazy[r] = None

            if (lo <= i and j <= hi):
                data[r] = v * (j - i + 1)
                if (i < j):
                    lazy[left] = v
                    lazy[right] = v
            else:
                mid = (i + j) // 2
                if (mid >= lo):
                    lv = _update(left, i, mid, lz)
                else:
                    if (lz is not None):
                        lazy[left] = lz
                        lv = lz * (mid - i + 1)
                    elif (lazy[left] is not None):
                        lv = lazy[left] * (mid - i + 1)
                    else:
                        lv = data[left]

                if (mid < hi):
                    rv = _update(right, mid + 1, j, lz)
                else:
                    if (lz is not None):
                        lazy[right] = lz
                        rv = lz * (j - mid)
                    elif (lazy[right] is not None):
                        rv = lazy[right] * (j - mid)
                    else:
                        rv = data[right]

                data[r] = lv + rv

            return data[r]

        data = self.data
        lazy = self.lazy
        _update(0, 0, self.size // 2, None)

    def sum(self, lo: int, hi: int) -> int:
        def _sum(r: int, i: int, j: int, lz: Optional[int]) -> int:
            if (lz is None):
                lz = lazy[r]
            lazy[r] = None
            if (lz is not None):
                data[r] = lz * (j - i + 1)

            left, right = r * 2 + 1, r * 2 + 2
            if (lo <= i and j <= hi):
                if (lz is not None and i < j):
                    lazy[left] = lz
                    lazy[right] = lz
                return data[r]
            else:
                mid = (i + j) // 2
                lv, rv = 0, 0

                if (mid >= lo):
                    lv = _sum(left, i, mid, lz)
                else:
                    if (lz is not None):
                        lazy[left] = lz
                if (mid < hi):
                    rv = _sum(right, mid + 1, j, lz)
                else:
                    if (lz is not None):
                        lazy[right] = lz
                return lv + rv

        data = self.data
        lazy = self.lazy
        return _sum(0, 0, self.size // 2, None)


if __name__ == "__main__":
    n, q = map(lambda x: int(x), input().split())
    segtree = SegmentTree(n)

    for _ in range(q):
        com, *v = map(lambda x: int(x), input().split())
        if (0 == com):
            segtree.update(v[0], v[1], v[2])
        else:
            print(segtree.sum(v[0], v[1]))

