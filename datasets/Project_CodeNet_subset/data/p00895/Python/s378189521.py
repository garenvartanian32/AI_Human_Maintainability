'''
7 0 1
6   2
5 4 3
'''
dx=[1,0,-1,0,1,-1,1,-1]
dy=[0,1,0,-1,1,1,-1,-1]

while True:
    h,w=map(int,input().split())
    if h==0:
        break
    field=[list(input()) for i in range(h)]
    allword=set()
    answord=set()
    for i in range(h):
        for j in range(w):
            s=field[i][j]
            if s in allword:
                answord.add((len(s),s))
            else:
                allword.add(s)
            for k in range(8):
                used=[[False for _ in range(w)]for _ in range(h)]
                s=field[i][j]
                used[i][j]=True
                while True:
                    i=(i+dy[k])%h
                    j=(j+dx[k])%w
                    if used[i][j]:
                        break
                    s+=field[i][j]
                    if s in allword:
                        answord.add((len(s),s))
                    else:
                        allword.add(s)
    print(sorted(answord,key=lambda x:(-x[0],x[1]))[0][1] if answord and sorted(answord,key=lambda x:(-x[0],x[1]))[0][0]>1 else 0 )






