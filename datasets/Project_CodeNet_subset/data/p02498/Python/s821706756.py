import sys
str = raw_input()
for i in str:
  if i.isupper():
    sys.stdout.write(i.lower())
  else:
    sys.stdout.write(i.upper())
print ""