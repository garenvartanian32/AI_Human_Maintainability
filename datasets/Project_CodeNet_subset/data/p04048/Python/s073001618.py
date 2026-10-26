n,x = map(int,input().split())
ans = n
e = n-x
while e > 0:
    if x%e == 0:    
        ans += x//e*(e*2)-e
    else:
        ans += (x//e)*e*2
    x,e = e,x%e    
print(ans)