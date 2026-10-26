a,b=map(int,input().split())

if 0<a<b:
    print('Positive')

elif a<=0<=b:
    print('Zero')

elif (b-a)%2==0:
    print('Negative')
else:
    print('Positive')