n,m = map(int,input().split())
L = []
for i in range(n):
    L.extend(list(map(int,input().split()))[1:])
if len(set(L)) == m:
    print("YES")
else:
    print("NO")