#!/usr/bin/env python
# coding: utf-8


def main():
    W = raw_input().lower()
    T = ""
    while 1:
        t = raw_input()
        if (t == "END_OF_TEXT"):
            break
        T += t + " "
    L = T.lower().split()
    count = 0
    for word in L:
        if word == W:
            count += 1
    print count


if __name__ == '__main__':
    main()