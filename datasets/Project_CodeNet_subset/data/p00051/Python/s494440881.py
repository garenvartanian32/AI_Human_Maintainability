#!/usr/bin/env python
# coding: utf-8

import sys

def get_answer(param):
    param = list(param)
    param.sort()
    small = int("".join(param), 10)
    big = int("".join(param[::-1]), 10)
    return big - small
    
def main():
    params = [line.replace("\n", "") for line in sys.stdin.readlines()]
    params.pop(0)

    for param in params:
        print get_answer(param)

if __name__ == '__main__':
    main()