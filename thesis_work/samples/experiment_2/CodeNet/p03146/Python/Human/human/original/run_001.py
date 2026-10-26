s=int(input())
seen=set()
seen.add(s)
cnt=1
while True:
  if s%2==0:
    s//=2
  else:
    s=s*3+1
  cnt+=1
  if s in seen:
    print(cnt)
    break
  else:
    seen.add(s)
