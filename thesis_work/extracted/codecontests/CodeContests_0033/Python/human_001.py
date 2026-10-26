one=input()
num=int(input())
twos=[]
for i in range(num):
  twos.append(input())

if (one in twos) or (one[::-1] in twos):
  print("YES")

else:
  flag1,flag2=False,False
  for i in range(num):
    if twos[i][0]==one[1]:
      flag1=True
    if twos[i][1]==one[0]:
      flag2=True
  if(flag1 and flag2):
    print("YES")
  else:
    print("NO")