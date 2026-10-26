def baseN(num,b,numerals="0123456789abcdefghijklmnopqrstuvwxyz"):
    return ((num == 0) and numerals[0]) or (baseN(num // b, b, numerals).lstrip(numerals[0]) + numerals[num % b])
n=int(input())
for i in range(1,n):
	for j in range(1,n):
		print(baseN(i*j,n),end=" ")
	print()
