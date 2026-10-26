n = int(input())
arr = [int(i) for i in input().split(" ")]

m = max(arr)
s = 0
for each in arr:
    s = s + each
    


while True:
    ansv = (m*n) - s
    if ansv > s:
        print(m)
        break
    else:
        m = m + 1