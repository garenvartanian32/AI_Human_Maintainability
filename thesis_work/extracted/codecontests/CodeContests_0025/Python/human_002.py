n=int(input())
a=list([int(x) for x in input().split()] for i in range(n))
if n==1:
    sum1=1
else:
    sum1=2
    test=a[0][0]
    for i in range(1,n-1):
        if a[i][0]-a[i][1]>test:
            sum1=sum1+1
            test=a[i][0]
        else:
            if a[i][0]+a[i][1]<a[i+1][0]:
                sum1=sum1+1
                test=a[i][0]+a[i][1]
            else:
                test=a[i][0]
print(sum1)
