#!/usr/bin/python3
# -*- coding: utf-8 -*-

import sys

def bubble_sort(A):
    for i in range(len(A)):
        for x in range(len(A)-1, i, -1):
            if A[x][1] < A[x-1][1]:
                A[x], A[x-1] = A[x-1], A[x]

def selection_sort(A):
    for i in range(len(A)):
        min_val = A[i][1]
        for x in range(i, len(A)):
            if A[x][1] < min_val:
                min_val = A[x][1]
                min_index = x
        if min_val < A[i][1]:
            A[min_index], A[i] = A[i], A[min_index]

def check_stable(srt_list, org_list):
    prev_val = None
    prev_pos = -1
    for card in srt_list:
        cur_pos = org_list.index(card)
        # print(card, cur_pos)
        if card[1] == prev_val:
            if cur_pos < prev_pos:
                print('Not stable')
                return
        prev_val = card[1]
        prev_pos = cur_pos

    print('Stable')


n = int(sys.stdin.readline())
org_list = sys.stdin.readline().split()
_list1 = org_list[:]
_list2 = org_list[:]

bubble_sort(_list1)
selection_sort(_list2)

print(' '.join(_list1))
check_stable(_list1, org_list)
print(' '.join(_list2))
check_stable(_list2, org_list)

