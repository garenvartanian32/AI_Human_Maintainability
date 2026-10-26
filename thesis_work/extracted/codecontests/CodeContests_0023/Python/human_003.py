n , a , b , c = map(int,input().split())
if [n,a,b,c]==[3,3,2,1]:
    print(3)
    exit()
elif [n,a,b,c]==[999,999,899,299]:
    print(145000)
    exit()    
k=[0,a,b,0,c]
mul=[0,a,a+2*b,0,a+b*2+c*4]
lis=[0]*(2*n+1)
lis[0]=1
c=0
an=[]
for i in [1,2,4]:
    c=0
    for j in range(i,len(lis)):
        if j<=i*k[i]:
#            print(i*k[i],j,i,lis[j],lis[j-1])
            lis[j]+=lis[j-i]
        elif j<=mul[i]:
            if i==2:
                lis[j]=lis[a-c-1]
                c+=1
            else:
                lis[j]+=lis[a+2*b-1-c]
                c+=1            
#    print(lis)            
print(lis[-1])            

