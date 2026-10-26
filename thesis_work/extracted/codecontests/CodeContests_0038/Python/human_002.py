N = int(input())
d = {"AC":0,"WA":0,"TLE":0,"RE":0}
for n in range(N):
  s = input()
  d[s] += 1
for s in d:
  print(s + " x " + str(d[s]))
  