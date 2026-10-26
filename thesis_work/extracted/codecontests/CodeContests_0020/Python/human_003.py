cows = int(input())
bets = input()

i = bets.count('I')
if i > 1:
    print(0)
elif i == 1:
    print(1)
else:
    print(bets.count('A'))