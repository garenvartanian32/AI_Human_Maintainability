a=int(input())#no. of test cases
for i in range(a):
    l,r=map(int,input().split())
    s1 = set(map(int, input().split()))
    s2= set(map(int,input().split()))
    s3= s1.intersection(s2)
    if len(s3)!=0:
        print("Yes")
        print(1,min(s3))
    else:
        print("NO")