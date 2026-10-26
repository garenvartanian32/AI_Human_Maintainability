m, f, b = input().split()
m = int(m)
f = int(f)
b = int(b)

if m >= b:
  print(0)
elif m + f >= b:
  print(b - m)
else:
  print('NA')
