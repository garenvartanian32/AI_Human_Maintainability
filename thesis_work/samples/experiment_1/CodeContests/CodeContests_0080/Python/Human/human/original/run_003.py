from collections import Counter
from collections import defaultdict
import random
import string


a = input()
b = input()
c = input()

# if len(c) < len(b):b,c = c,b

counta = {}
countb = {}
countc = {}
for i in "abcdefghijklmnopqrstuvwxyz":
    counta[i]=0
    countb[i]=0
    countc[i]=0

for i in a:counta[i]+=1
for i in b:countb[i]+=1
for i in c:countc[i]+=1

maxV = 0
count_of_b = 0
count_of_c = 0
opop=0

tempCounta = {}
for i,j in counta.items():tempCounta[i]=j
_ = 0
while(True):
    temp = len(a)

    for i in countc : 
        if countc[i]!=0: 
            temp = min(temp,tempCounta[i]//countc[i])

    if _ + temp>maxV:
        # print("testing-->",count_of_b,count_of_c)
        count_of_b = _
        count_of_c = temp
        maxV = _ + temp

    weCanProceed = True

    for i in countb:
        if tempCounta[i] < countb[i]: 
            weCanProceed=False
            break
    
    if not weCanProceed: break
    else : 
        for i in countb : tempCounta[i]-=countb[i]
    _+=1

if count_of_c>0:
    for i in countc:
        counta[i]-=count_of_c*countc[i]

if count_of_b>0:
    for i in countb:
        counta[i]-=count_of_b*countb[i]

ans = b*count_of_b + c*count_of_c
for i in counta:
    ans+=i*counta[i]

print(ans)

