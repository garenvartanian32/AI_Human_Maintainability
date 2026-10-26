ax,ay,bx,by,cx,cy=map(int,input().split())
ab=(ax-bx)**2+(ay-by)**2
bc=(bx-cx)**2+(by-cy)**2
if ab==bc and (ay-by)*(bx-cx)!=(by-cy)*(ax-bx):
    print("Yes")   
else:
    print("No")
