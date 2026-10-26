s=input()
l=[]
l.append(s)
s=s.split()
for i in range(int(input())):
	a,b=input().split()
	s[s.index(a)]=b
	l.append(" ".join(s))
print(*l,sep="\n")