n,m=map(int,input().split())
d={}
for i in range(n):
      a,b=map(int,input().split())
      if a in d:
            d[a]+=b
      else:
            d[a]=b
d = list(sorted(d.items(), key=lambda x:x[0]))

dd=[]
for i in d:
      dd.append([i[0],i[1]])

ans=0
i=0
c=0
while m>c:
      if dd[i][1]!=0:
            dd[i][1]+=-1
            ans+=dd[i][0]
            c+=1
      else:
            i+=1
     
print(ans)
