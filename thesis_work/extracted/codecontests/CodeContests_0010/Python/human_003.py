for t in range(int(input())):
    n=int(input())
    a=list(map(int,input().split()))
    c=0
    for i in range(2,n): 
        l = i   
        r =n-1 
        if a[0]+a[1]<=a[l]:
            c+=1
            print(1,2,i+1)
            break
    if c==0:
        print(-1)
    