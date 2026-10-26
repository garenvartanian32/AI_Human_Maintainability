#!/usr/bin/env python3
import io
import os

input = io.BytesIO(os.read(0,os.fstat(0).st_size)).readline

def get_str():
    return input().decode().strip()

def rint():
    return map(int, input().split())

def oint():
    return int(input())

t = oint()

for _ in range(t):
    n, m = rint()
    a = set(rint())
    b = set(rint())
    c = a&b
    if c:
        print('YES')
        print(1, list(c)[0])
    else:
        print('NO')

