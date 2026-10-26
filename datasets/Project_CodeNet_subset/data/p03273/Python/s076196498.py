#!/usr/bin/env python3
# -*- coding: utf-8 -*-
import numpy as np


def main():
    H, W = map(int, input().split())
    # A = [list(input()) for _ in range(H)]
    A = np.array([list(input()) for _ in range(H)])
    A = A[:, ~np.all(A == '.', axis=0)]
    A = A[~np.all(A == '.', axis=1), :]
    [print(''.join(A[i, :])) for i in range(np.shape(A)[0])]


if __name__ == "__main__":
    main()
