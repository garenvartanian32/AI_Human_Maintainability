a = []
for i in range(20):
    n = int(input())
    a.append(n)
x = a[0:10]
y = a[10:20]
X = sorted(x, reverse=True)
Y = sorted(y, reverse=True)
b = X[0]+X[1]+X[2]
c = Y[0]+Y[1]+Y[2]
print(b, c)
