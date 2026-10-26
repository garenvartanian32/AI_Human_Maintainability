a,b,c=map(int,input().split())
t=c*a//b
le=0
ri=10**10
while le+1<ri:
    m=(le+ri)//2
    k=m*b%a
    flag=1
    t=m*b//a
   
    for i in range(1,c+1):
      
        k+=b
        t+=k//a
        k%=a
        t-=1
        if t<0:
            flag=0
            break
        
    if flag==0:
        le=m
    else:
        
        ri=m
print(ri)
            
