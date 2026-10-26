p = "A"
while 1:
	try: a, b = list(map(str, input().split(",")))
	except: break
	if a == p: p = b
	elif b == p: p = a
print(p)
