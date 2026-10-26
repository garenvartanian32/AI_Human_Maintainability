n = int(input())
r = 'a',
for _ in range(n - 1):
  r = [s + c for s in r for c in set(s + chr(ord(max(s)) + 1))]
print(*sorted(r), sep='\n')