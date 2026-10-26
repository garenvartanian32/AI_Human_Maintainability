x, y=[int(x) for x in input().split()]
b=0
h=0
while x>0:
    x-=1
    b+=1
    if b==y:
        b=0
        x+=1
    h+=1
print(h)