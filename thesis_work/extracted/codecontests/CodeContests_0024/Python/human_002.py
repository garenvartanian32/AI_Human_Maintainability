n=int(input())
l=input().split()
l1=input().split()
l2=input().split()
for i in range(n):
    l[i]=int(l[i])
for i in range(n-1):
    l1[i]=int(l1[i])
for i in range(n - 2):
    l2[i] = int(l2[i])
l.sort()
l1.sort()
l2.sort()
#print(l,l1,l2)
x=0
y=0
for i in range(n-1):
    if l[i]!=l1[i]:
        x=l[i]
        break
    if x==0:x=l[n-1]
for i in range(n-2):
    if l1[i]!=l2[i]:
        y=l1[i]
        #rint(i)
        break
    if y==0:y=l1[n-2]
print(x)
print(y)