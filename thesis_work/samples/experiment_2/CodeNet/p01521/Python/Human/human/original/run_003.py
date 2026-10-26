s=input()
while "oo" in s or "xx" in s:s=s.replace("oo","o").replace("xx","x")
if len(s)%2==1:print(s[0])
else:print("o")
