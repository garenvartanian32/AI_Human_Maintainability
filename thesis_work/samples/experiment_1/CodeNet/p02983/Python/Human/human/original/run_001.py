L, R = map(int, input().split())
if R >= L+2019:
    print(0)
    quit()
L %= 2019
R %= 2019
ans = 2020
if L > R:
    t = L
    L = R
    R = t
if L == R:
    print(L*L%2019)
    quit()
ans = 2020
for i in range(L, R):
    for j in range(i+1, R+1):
        ans = min(ans, i*j%2019)
print(ans % 2020)
