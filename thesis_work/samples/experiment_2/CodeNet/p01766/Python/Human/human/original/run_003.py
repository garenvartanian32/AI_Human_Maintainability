N = int(raw_input())
#ball[t] = [f,a,x,y]
ball = [[0,0,0,0],[0,0,0,0]]
#ans[t] = [dist,flame]
ans = [[-1,-1],[-1,-1]]
bt = -1
for loop in xrange(N):
    f,a,t,x,y = map(int,raw_input().split())
    if bt == t and ball[t][0] != a :
        bf,bx,by = ball[t][1:]
        dist = (x-bx)**2+(y-by)**2
        if   dist  > ans[t][0]:
            ans[t] = [dist,f-bf]
        elif dist == ans[t][0]:
            ans[t][1] = min(ans[t][1], f-bf)
            
    ball[t] = [a,f,x,y]
    bt = t

if ans[0][0] == -1:
    print -1,-1
else:
    print "%.6f %.6f"%(ans[0][0]**0.5,ans[0][1]/60.0)
if ans[1][0] == -1:
    print -1,-1
else:
    print "%.6f %.6f"%(ans[1][0]**0.5,ans[1][1]/60.0)