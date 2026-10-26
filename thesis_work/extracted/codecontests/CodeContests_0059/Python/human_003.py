n = int(input())
s1 = input()
s = list(s1)
f = 0
i = 0
while(ord(s[i])<=ord(s[i+1])):
    i+=1
    if((i+1)>=n):
        print("NO")
        f = 1
        break
if(f==0):
    j = i+1
    while(ord(s[j])>ord(s[i])):
        j+=1
    print("YES")
    print(i+1,j+1)