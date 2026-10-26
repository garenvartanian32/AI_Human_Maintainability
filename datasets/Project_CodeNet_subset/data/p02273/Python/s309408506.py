N = int(input())

def kccv(a, b, c, d):
    return [a*2/3 + c/3, b*2/3 + d/3, a/2 + b*(3**0.5)/6 + c/2 - d*(3**0.5)/6, -a*(3**0.5)/6 + b/2 + c*(3**0.5)/6 + d/2, a/3 + c*2/3, b/3 + d*2/3]

ans = [[[0,0],[100,0]]]

for i in range(1,N+1):
    m = len(ans[i-1])
    newset = []
    for j in range(m-1):
        newset.append(ans[i-1][j])
        newdot = kccv(ans[i-1][j][0], ans[i-1][j][1], ans[i-1][j+1][0], ans[i-1][j+1][1])
        x1 = newdot[0]
        y1 = newdot[1]
        x2 = newdot[2]
        y2 = newdot[3]
        x3 = newdot[4]
        y3 = newdot[5]
        newset.append([x1,y1])
        newset.append([x2,y2])
        newset.append([x3,y3])
    newset.append(ans[i-1][m-1])
    ans.append(newset)
           
    
for i in range(len(ans[N])):
    print(ans[N][i][0],ans[N][i][1])
