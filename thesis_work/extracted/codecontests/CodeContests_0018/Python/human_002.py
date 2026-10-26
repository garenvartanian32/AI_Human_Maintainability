s=[int(n) for n in  input().split()]
if s[0]==0 and s[1]*s[2]!=0:
	print(1)
	print(-s[2]/s[1])
elif s[0]*s[1]!=0 and s[2]==0:
	
		print(2)
		a=0
		b=-s[1]/s[0]
		p=[a,b]
		print('%6f'%min(p))
		print('%6f'%max(p))
elif s[0]*s[2]<0 and s[1]==0:
	print(2)
	p=[(-s[2]/s[0])**.5,-(-s[2]/s[0])**.5]
	print('%.6f'%min(p))
	print('%.6f'%max(p))
elif s[0]==0 and s[1]==0 and s[2]!=0:
	print(0)
elif s[0]==s[1]==s[2]==0:
	print(-1)
elif s[0]*s[1]*s[2]!=0:
	if s[1]**2<4*(s[0]*s[2]):
		print(0)
	elif s[1]**2==4*(s[0]*s[2]):
		print(1)
		print('%.6f'%(-s[1]/(2*s[0])))
	else:
		print(2)
		j=(-s[1]+(s[1]**2-4*s[0]*s[2])**.5)/(2*s[0])
		k=(-s[1]-(s[1]**2-4*s[0]*s[2])**.5)/(2*s[0])
		p=[j,k]
		print("%.6f"%min(p))
		print('%.6f'%max(p))
elif s[0]==s[2]==0 and s[1]!=0:
	print(1)
	print('%6f'%0)
elif s[2]==s[1]==0 and s[0]!=0:
	print(1)
	print('%6f'%0)
elif s[0]*s[2]!=0 and s[1]==0:
	if s[0]*s[2]>0:
		print(0)
	else:
		print(2)
		print('%6f'%-(-s[2]/s[0])**.5)
		print('%6f'%(-s[2]/s[0])**.5)