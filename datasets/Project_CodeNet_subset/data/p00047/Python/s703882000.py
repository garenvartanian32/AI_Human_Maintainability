cup = "ABC"
while True :
    try :
        x, y = input().split(",")
        cup = cup.replace(x, "D")
        cup = cup.replace(y, x)
        cup = cup.replace("D", y)
    except EOFError :
        break
print(cup[0])
