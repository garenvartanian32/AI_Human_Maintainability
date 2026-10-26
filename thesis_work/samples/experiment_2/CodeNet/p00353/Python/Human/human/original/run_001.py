m,f,b = list(map(int, input().split()))

t = b - m
if t>0:
    if t<=f: print(t)
    else: print("NA")
else: print(0)
