c = int(input())
heaps = []
mx = []

for i in range(0, c):
    mx.append(-2)

for i in range(0, c):
    heaps.append(input().split(" "))
for i in range(0, c):
    for j in range(0, len(heaps[i])):
        heaps[i][j] = int(heaps[i][j])
for i in range(0, c):
    a = heaps[i][0]
    b = heaps[i][1]
    c = heaps[i][2]
    summ = 0
    while c >= 2 and b >= 1:
        b = b-1
        c = c-2
        summ = summ+3
    while b >= 2 and a >= 1:
        a = a-1
        b = b-2
        summ = summ+3
    mx[i] = summ
    print(summ)
