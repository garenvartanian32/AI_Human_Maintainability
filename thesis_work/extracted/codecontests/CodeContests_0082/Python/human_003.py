c =0
o = 0
while True:
    try:
        cmd = input()
        if cmd[0] == "+":
            c+=1
        elif cmd[0] == "-":
            c-=1
        else:
            o+= len(cmd.split(":")[1])*c
    except :
        break
print(o)