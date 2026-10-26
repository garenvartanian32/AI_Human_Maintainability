a, v = map(int, input().split())
b, w = map(int, input().split())
t = int(input())


c=abs(a-b)-v*t+w*t
if c<=0:
    print("YES")
else:
    print("NO")