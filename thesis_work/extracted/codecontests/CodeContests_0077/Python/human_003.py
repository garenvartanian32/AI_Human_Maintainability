x = input().split()

stacks =[int(i) for i in x]

if sum(stacks) % 5 != 0 or sum(stacks) == 0:
    print(-1)

else:
    print(int(sum(stacks) / 5))

