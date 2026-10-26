# -*- coding: utf-8 -*-
"""
http://judge.u-aizu.ac.jp/onlinejudge/description.jsp?id=DPL_4_A
AC
"""
import sys
from sys import stdin
from bisect import bisect_right, bisect_left
input = stdin.readline


def main(args):
    N, V = map(int, input().split())
    A = [int(x) for x in input().split()]
    B = [int(x) for x in input().split()]
    C = [int(x) for x in input().split()]
    D = [int(x) for x in input().split()]

    AB = tuple(a+b for a in A for b in B)

    CD = [c+d for c in C for d in D]
    CD.append(float('-inf'))
    CD.append(float('inf'))
    CD.sort()

    count = 0
    for ab in AB:
        i = bisect_left(CD, V - ab)
        j = bisect_right(CD, V - ab)
        count += (j-i)

    print(count)


if __name__ == '__main__':
    main(sys.argv[1:])