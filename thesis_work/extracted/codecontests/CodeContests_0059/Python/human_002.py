n = int(input())
s = input()
maxi = -1
f = False
for i in range(n):
    if maxi != -1 and s[i] < s[maxi]:
        print('YES')
        print(maxi+1, i+1)
        f = True
        break
    else:
        if maxi == -1:
            maxi = i
        elif s[i] > s[maxi] :
            maxi = i
if not f:
    print('NO')
