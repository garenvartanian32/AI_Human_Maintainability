N=int(input())

a = list(map(int,input().split()))
b = list(map(int,input().split()))

s=sum(b)-sum(a)

if s<0:
    print("No")
else:
    A = 2*s
    B = s
    for i in range(N):
        if a[i]-b[i]<0:
            if (a[i]-b[i])%2==0:
                A+=a[i]-b[i]
            else:
                A+=a[i]-b[i]-1
                B-=1
        else:
            B-=a[i]-b[i]
        #print(A,B)
    if A<0 or B<0:
        print("No")
    else:
        print("Yes")