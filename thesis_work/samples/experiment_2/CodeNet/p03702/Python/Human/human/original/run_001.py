N,A,B = map(int,input().split())
h = [int(input()) for _ in range(N)]

left = 1
right = max(h)
import math
def judge(x):
    cnt = 0
    tmp = 0
    for i in range(N):
        tmp = h[i] - B * x
        if tmp > 0:
            cnt += math.ceil(tmp/(A-B))
    if cnt <= x:
        return True
    else:
        return False
    
def binary(left,right):
    c = (left + right)//2
    if left >= right:
        for i in range(3):
            idx = left -1 + i
            if judge(idx):
                return (idx)
    if judge(c):
        right = c -1
        return binary(left,right)
    else:
        left = c + 1
        return binary(left,right)
    
    
ans = binary(left,right)
print(ans)
    
