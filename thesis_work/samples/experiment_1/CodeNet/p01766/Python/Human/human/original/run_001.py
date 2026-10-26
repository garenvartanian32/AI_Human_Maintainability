import math

n = int(input())

prev = list(map(int, input().split()))
max_disA = -1
max_disB = -1
min_timeA = 10**9
min_timeB = 10**9

for _ in range(n-1):
    curr = list(map(int, input().split()))
    if prev[1] != curr[1] and prev[2] == curr[2]:
        dis = math.sqrt((prev[3] - curr[3])**2 + (prev[4] - curr[4])**2)
        if max_disA < dis and prev[2] == 0:
            max_disA = dis
            min_timeA = curr[0] - prev[0]
        elif max_disB < dis and prev[2] == 1:
            max_disB = dis
            min_timeB = curr[0] - prev[0]
        elif dis == max_disA and prev[2] == 0 and min_timeA > curr[0] - prev[0]:
            max_disA = dis
            min_timeA = curr[0] - prev[0]
        elif dis == max_disB and prev[2] == 0 and min_timeB > curr[0] - prev[0]:
            max_disB = dis
            min_timeB = curr[0] - prev[0]
    prev = curr

if max_disA == -1:
    print(-1, -1)
else:
    print(max_disA, min_timeA / 60)

if max_disB == -1:
    print(-1, -1)
else:
    print(max_disB, min_timeB / 60)
