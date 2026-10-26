def cal(x, li, sign):
    sum = 0
    ans = 0
    for i in range(0,n):
        sum += (li[i]-x)*sign
        if sum < 0:
            sum = 0
        if sum > ans:
            ans = sum
    return ans

n = int(input())
li = [int(x) for x in input().split()]

r = max(li)
l = min(li)

ans = -1
mn = 99999999

for i in range(30):
    k = (r+l)/2
    a1 = cal(k, li, 1)
    a2 = cal(k, li, -1)
    if abs(a1) < abs(a2):
        r = k
    else:
        l = k
    if abs(abs(a1)-abs(a2)) < mn:
        mn = abs(abs(a1)-abs(a2))
        ans = abs(a1)
        if abs(a2) > abs(a1):
            ans = abs(a1)
    #print('a1='+str(a1))
    #print('a2='+str(a2))
    if r-l < 1e-6:
        break

print(ans)
