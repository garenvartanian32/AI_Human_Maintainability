while(True):
    n = input()
    if n == '0': break
    line = input()
    qs = line.split(" ")
    up = 0
    down = 0
    cnt = 0
    for q in qs:
        if q == "lu" or q == "ru" :
            up += 1
            down = 0
        else:
            down += 1
            up = 0
        if up==2 or down == 2:
            cnt += 1
    print(cnt)

