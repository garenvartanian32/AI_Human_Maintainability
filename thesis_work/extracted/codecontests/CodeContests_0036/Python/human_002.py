n = int(input())
a = list(map(int,input().split()))
p1 = 1
p2 = 1000000
i = 0
j = 1
c = 0
while i+j<=n:
    t1 = a[i] - 1
    t2 = 1000000 - a[-j]
    if t1<t2:
        p1 = a[i]
        i+=1
    elif t1>t2:
        p2 = a[-j]
        j+=1
    else:
        p1 = a[i]
        i+=1
        p2 = a[-j]
        j+=1

print(max(p1 - 1,1000000-p2))