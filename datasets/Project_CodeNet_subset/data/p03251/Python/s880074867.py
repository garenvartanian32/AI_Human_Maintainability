LL = input().split(" ")

LX = list(map(int,input().split()))
LY = list(map(int,input().split()))
	
LX.sort(reverse = True)
LY.sort()

CO = 0

for R in range(int(LL[2])+1,int(LL[3])):
	if LX[0]  >= LY[0]:
		break
	elif LX[0] <= R  <= LY[0] :
		print("No War")
		CO = 1
		break
		
if CO == 0:
	print("War")