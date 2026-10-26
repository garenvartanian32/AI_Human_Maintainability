n = int(input())
a = list(map(int,input().split()))

if 0 in a:
    print(0)
    exit()

c = 1
for i in a:
    c *= i
    if c > 10 ** 18:
        print(-1)
        exit()

print(c)
