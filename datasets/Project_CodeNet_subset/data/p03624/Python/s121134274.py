myinput=input()
for c in 'abcdefghijklmnopqrstuvwxyz':
    if c not in myinput:
        print(c)
        break
else:
    print('None')