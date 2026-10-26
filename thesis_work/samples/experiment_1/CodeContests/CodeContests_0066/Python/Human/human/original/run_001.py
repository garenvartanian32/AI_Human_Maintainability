# -*- coding: utf-8 -*-
"""
Created on Sat Jul 10 23:15:34 2021

@author: Kevin Chang

Project: Codeforces Problem 1547C

"""

t = int(input())

for i in range(t):
    shit = input()
    k, n, m = list(map(int, input().split()))
    a = list(map(int, input().split()))
    b = list(map(int, input().split()))
    res = []
    ress = 1

    for aa in a:
        if ress == -1:
            break
        elif aa == 0:
            k += 1
            res.append(aa)
        elif aa > k:
            while k < aa:
                if len(b) == 0:
                    ress = -1
                    break
                elif b[0] == 0:
                    k += 1
                    res.append(b.pop(0))
                elif b[0] > k:
                    ress = -1
                    break
                else:
                    res.append(b.pop(0))
            res.append(aa)
        else:
            res.append(aa)
            continue

    if ress == -1:
        print(ress)
    else:
        for bb in b:
            if ress == -1:
                break
            elif bb == 0:
                k += 1
                res.append(bb)
            elif bb > k:
                ress = -1
            else:
                res.append(bb)
            continue
        if ress == -1:
            print(ress)
        else:
            print(*res)
