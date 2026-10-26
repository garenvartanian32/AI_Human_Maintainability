import copy

while True:
    n = input()
    if n == 0: break
    L = []
    for i in range(n):
        f, w, s = raw_input().split()
        L.append([f, int(w), int(s)])
    else:
        L = sorted(L, key=lambda x: int(x[1]), reverse=True)
    
    ans = []
    while len(L) != 1:
        for i in range(len(L)):
            _L = copy.deepcopy(L)
            f = _L[i][0]
            s = _L[i][2]
            del _L[i]
            t = sum([_L[o][1] for o in range(len(_L))])
            if t <= s:
                ans.append(f)
                del L[i]
                break
    else:
        ans.append(L[0][0])
        for line in ans:
            print line