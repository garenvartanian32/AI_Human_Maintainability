n=int(input())

ans=''

#n=n-1
while(n>0):
    n=n-1
    digit=n%26
    n=n//26
    ans+=chr(97+digit)

ans_rev=ans[::-1]
print(ans_rev)
