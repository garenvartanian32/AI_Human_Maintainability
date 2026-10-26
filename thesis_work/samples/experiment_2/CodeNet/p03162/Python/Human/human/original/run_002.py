import copy

N=int(input())
ABC=[list(map(int,input().split())) for i in range(N)]

NOW=[0,0,0]#最後にしたことそれぞれについての幸福度

NEXT=[None]*3
for i in range(N):
    NEXT[0]=max(NOW[1],NOW[2])+ABC[i][0]
    NEXT[1]=max(NOW[0],NOW[2])+ABC[i][1]
    NEXT[2]=max(NOW[1],NOW[0])+ABC[i][2]

    NOW=copy.copy(NEXT)
    #print(NOW)
        
print(max(NOW))