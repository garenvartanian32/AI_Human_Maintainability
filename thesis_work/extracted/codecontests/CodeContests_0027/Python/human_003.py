n, m = map(int, input().split())

bulbs = [None] * m 

for i in range(n):
    array = list(map(int, input().split()))
    for j in range(1,array[0] + 1):
        bulbs[array[j] - 1] = 1
        
result = True
for i in range(m):
    if bulbs[i] is None:
        result = False
        break
    
if result:
    print("YES")
else:
    print("NO")
