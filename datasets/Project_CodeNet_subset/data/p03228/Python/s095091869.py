a,b,k = map(int,input().split())

tmp=1
for i in range(k):
    if tmp==1:
        b+=a//2
        a=a//2
        tmp=2
    else:
        a+=b//2
        b=b//2
        tmp=1

print(a,b)