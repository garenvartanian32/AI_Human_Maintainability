s = list(map(str, input().split('-')))
dic = {}
d = {1:31, 2:28, 3:31,4: 30, 5:31, 6:30, 7:31, 8:31, 9:30, 10:31, 11:30, 12:31}
for i in range(len(s)-2):
    if len(s[i])>=2:
        if len(s[i+1])==2 and int(s[i+1])<=12 and int(s[i+1])>=1 and int(s[i][-2]+s[i][-1])<=d[int(s[i+1])] and int(s[i][-2]+s[i][-1])>=1 and len(s[i+2])>=4 and int(s[i+2][:4])>=2013 and int(s[i+2][:4])<=2015:
            st = s[i][-2]+s[i][-1]+'-'+s[i+1] + '-' + s[i+2][:4]
            try:
                dic[st]+=1
            except:
                dic[st]=1
max = 0
ind = 0
for i in dic:
    if max<dic[i]:
        max = dic[i]
        ind = i
print(ind)
