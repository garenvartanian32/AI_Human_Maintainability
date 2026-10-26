N = input()
for i in range(N):
  s = raw_input()
  p, r, l, a= 50, 50, 50, [s[0]]
  for c in s:
    if c == '-':
      pass
    elif c == '>':
      p += 1
    elif c == '<':
      p -= 1
    else:
      if p > r:
        a.append(c)
        r = p
      elif p < l:
        a.insert(0, c)
        l = p
  print(''.join(a))