n=int(input())
d,z=[],n
for x in range(2,int(n**0.5)+1):
    while n%x==0: n//=x; d.append(x)
if n>1 and n!=z: d.append(int(n))
if len(d)==2:
    print(2)
elif len(d)<2:
    print(1)
    print(0)
else:
    print(1)    
    print(d[0]*d[1])

