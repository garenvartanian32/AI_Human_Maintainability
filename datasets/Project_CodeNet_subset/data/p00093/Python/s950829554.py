import calendar
c = 0
while 1:
	f = 0
	(n,m) = map(int,raw_input().split())
	if (n == 0 and m == 0):
		break;
	if c == 1:
		print ""
	for i in range(n,m+1):
		if calendar.isleap(i):
			f = 1
			print i
	if f == 0:
		print "NA"
	c = 1