t=int(input())
for i in range(t):
    n=int(input())
    l=[]
    for i in range(n):
        l.append(list(input()))
    if l[0][1]=='0' and l[1][0]=='0':
        if l[n-2][n-1]=='0' and l[n-1][n-2]=='0':
            print(2)
            print(n-1,n)
            print(n,n-1)
        elif l[n-2][n-1]=='0' and l[n-1][n-2]=='1':
            print(1)
            print(n-1,n)
        elif l[n-2][n-1]=='1'and l[n-1][n-2]=='0':
            print(1)
            print(n,n-1)
        elif l[n-2][n-1]=='1' and l[n-1][n-2]=='1':
            print(0)
    elif l[0][1]=='0' and l[1][0]=='1':
        if l[n-2][n-1]=='0' and l[n-1][n-2]=='0':
            print(1)
            print(1,2)
        elif l[n-2][n-1]=='0' and l[n-1][n-2]=='1':
            print(2)
            print(1,2)
            print(n,n-1)
        elif l[n-2][n-1]=='1' and l[n-1][n-2]=='0':
            print(2)
            print(1,2)
            print(n-1,n)
        elif l[n-2][n-1]=='1' and l[n-1][n-2]=='1':
            print(1)
            print(2,1)
    elif l[0][1]=='1' and l[1][0]=='0':
        if l[n-2][n-1]=='0' and l[n-1][n-2]=='0':
            print(1)
            print(2,1)
        elif l[n-2][n-1]=='0' and l[n-1][n-2]=='1':
            print(2)
            print(2,1)
            print(n,n-1)
        elif l[n-2][n-1]=='1'and l[n-1][n-2]=='0':
            print(2)
            print(2,1)
            print(n-1,n)
        elif l[n-2][n-1]=='1' and l[n-1][n-2]=='1':
            print(1)
            print(1,2)
    elif l[0][1]=='1' and l[1][0]=='1':
        if l[n-2][n-1]=='0' and l[n-1][n-2]=='0':
            print(0)
        elif l[n-2][n-1]=='0' and l[n-1][n-2]=='1':
            print(1)
            print(n,n-1)
        elif l[n-2][n-1]=='1' and l[n-1][n-2]=='0':
            print(1)
            print(n-1,n)
        elif l[n-2][n-1]=='1' and l[n-1][n-2]=='1':
            print(2)
            print(n-1,n)
            print(n,n-1)
            
            
        
    
    
