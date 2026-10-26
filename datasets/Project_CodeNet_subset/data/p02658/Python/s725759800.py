n = int(input())
A = map(int,input().split())
 
ans = 1
for num in A:
    ans*=num
    if (ans > 10**18):
        ans = -1
        break
if (0 in A):
    ans = 0;
print(ans)