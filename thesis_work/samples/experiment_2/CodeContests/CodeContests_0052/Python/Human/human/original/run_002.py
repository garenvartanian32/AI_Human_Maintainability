while 1:
    n = int(input())
    if n == 0:
        break
    f = list(map(str,input().split()))
    cnt = 0
    for i in range(n-1):
        if (i+1) % 2 == 1:
            if f[i] == "lu" and f[i+1] =="ru":
                cnt += 1
            if f[i] == "ru" and f[i+1] == "lu":
                cnt += 1
            if f[i] == "ld" and f[i+1] == "rd":
                cnt += 1
            if f[i] == "rd" and f[i+1] == "ld":
                cnt += 1
    print(cnt)


