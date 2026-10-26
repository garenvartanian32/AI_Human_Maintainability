# coding: utf-8
# Here your code !

a = list(map((int), input().split(' ')))
N, M = a[0], a[1]

first = []
second = []

for i in range(M):
    
    b = list(map((int),input().split(' ')))
    start, end = b[0], b[1]
    
    if start == 1:
        first.append(end)
    if end == N:
        second.append(start)
        
first_set = set(first)
second_set = set(second)

matched_l = list(first_set & second_set)

if matched_l is None or len(matched_l) == 0:
    print('IMPOSSIBLE')
else:
    print('POSSIBLE')
