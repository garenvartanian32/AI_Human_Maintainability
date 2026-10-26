w = raw_input().lower()
c = 0
while True:
    s = raw_input()
    if s == 'END_OF_TEXT': break
    c += s.lower().split().count(w)
print c