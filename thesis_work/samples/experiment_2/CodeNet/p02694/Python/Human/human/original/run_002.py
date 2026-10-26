X = int(input())

P = 100
step = 0
while P < X:
    P += P // 100
    step += 1

print(step)
