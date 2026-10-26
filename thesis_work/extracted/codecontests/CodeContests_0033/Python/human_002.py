password = input()
n = int(input())
lex = []
for i in range(n):
    lex.append(input())
YES = False
YES1 = False
YES2 = False
for word in lex:
    if word == password:
        YES = True
    if password[0] == word[1]:
        YES1 = True
    if password[1] == word[0]:
        YES2 = True
if YES1 and YES2:
    YES = True
if YES:
    print('YES')
else:
    print('NO')
