while True:
    n = int(input())
    if n == 0:
        break
    count = [0] * n
    for i in range(n):
        count[i] = len(input())
    for i in range(n-4):
        ind = i
        fin = True
        for tmp in [5, 7, 5, 7, 7]:
            while tmp > 0 and ind < n:
                tmp -= count[ind]
                ind += 1
            if tmp != 0:
                fin = False
                break
        if fin:
            print(i+1)
            break

