s = list(input())
alp = list("abcdefghijklmnopqrstuvwxyz")
for i in s:
    for j in range(len(alp)):
        if i == alp[j]:
            alp.pop(j)
            break
if len(alp) != 0: print(alp[0])
else: print("None")