n = int(input())
l = [int(x)//100 for x in input().split()]
a = l.count(2)
b = l.count(1)
c = a&1

if(b&1):
    print("NO")
else:
    if(c == 0):
        print("YES")
    else:
        if(b>=2):
            print("YES")
        else:
            print("NO")