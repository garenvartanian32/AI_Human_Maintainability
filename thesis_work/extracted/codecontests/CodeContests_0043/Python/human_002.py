#!/usr/bin/env python3
# -*- coding: utf-8 -*-

from math import ceil

def gcd(a, b):
    assert a > 0 and b > 0
    while b > 0:
        r = a % b
        a = b
        b = r
    return a

def solve(A, B, C, D):
    if A < B or D < B: return False
    if A % B > C: return False
    if D % B == 0: return True

    g = gcd(B, D)
    r = A % g  # +D -B を繰り返すと x % g == r であるxを訪れる
    # x % g == r and C < x < B である x が存在するなら False を返す
    return not (C < B - g + r)

T = int(input())
for i in range(T):
    A, B, C, D = [int(t) for t in input().split()]
    print('Yes' if solve(A, B, C, D) else 'No')
