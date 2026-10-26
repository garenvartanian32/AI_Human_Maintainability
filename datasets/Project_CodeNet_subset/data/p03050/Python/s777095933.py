def makediv(n):
  ret=[]
  for i in range(1,int(n**0.5)+1):
    if n%i==0:
      ret.append(i)
      if i!=n//i:
        ret.append(n//i)
  return ret
 
n=int(input())
arr=sorted(makediv(n),reverse=True)
ans=0
for i in arr:
  q=n//i
  if i-1<=q:
    break
  ans+=i-1
print(ans)