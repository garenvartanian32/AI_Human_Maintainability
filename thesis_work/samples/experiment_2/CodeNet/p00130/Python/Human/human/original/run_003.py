n=input()
while n:
  s=raw_input()
  A=s[0]
  for i in range(0,len(s),3):
    if s[i:i+3]==(A[-1]+"->"): A+=s[i+3]
    elif s[i:i+3]==(A[0]+"<-"): A=s[i+3]+A
  print "".join(A)
  n-=1