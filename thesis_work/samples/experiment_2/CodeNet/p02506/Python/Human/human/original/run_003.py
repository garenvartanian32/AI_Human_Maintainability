w = input().strip().lower()
text = []
while True:
    t = input().strip()
    if t == 'END_OF_TEXT': break
    text += t.lower().split()
print(text.count(w))