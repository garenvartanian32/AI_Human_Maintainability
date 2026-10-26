import math
x1,y1 = map(int,input().split())
x2,y2 = map(int,input().split())
x3,y3 = map(int,input().split())
ans = 0
for (xa,ya,xb,yb,xn,yn) in (x1,y1,x2,y2,x3,y3),(x2,y2,x3,y3,x1,y1),(x3,y3,x1,y1,x2,y2):
  xa -= xn
  ya -= yn
  xb -= xn
  yb -= yn
  xab = xa-xb
  yab = ya-yb
  L = math.hypot(xab,yab)
  cosa = (xab*xa+yab*ya)/(math.hypot(xa,ya)*math.hypot(xab,yab))
  cosb = (-xab*xb-yab*yb)/(math.hypot(xb,yb)*math.hypot(xab,yab))
  anga = math.acos(cosa)
  angb = math.acos(cosb)
  tana = math.tan(anga/2)
  tanb = math.tan(angb/2)
  ans = max(ans,L/(2+1/tana+1/tanb))
print(ans)