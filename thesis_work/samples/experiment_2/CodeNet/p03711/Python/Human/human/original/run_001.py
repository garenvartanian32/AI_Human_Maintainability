x, y = map(int, input().split())

import calendar

x, lastday_x = calendar.monthrange(2020,x)
y, lastday_y = calendar.monthrange(2020,y)

if lastday_x == lastday_y:
  print("Yes")
else:
  print("No")