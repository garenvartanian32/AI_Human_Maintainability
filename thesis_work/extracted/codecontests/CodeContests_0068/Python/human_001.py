from collections import Counter
import string
import math
import sys
def array_int():
    return [int(i) for i in sys.stdin.readline().split()]
def vary(number_of_variables):
    if number_of_variables==1:
        return int(sys.stdin.readline())
    if number_of_variables>=2:
        return map(int,sys.stdin.readline().split()) 
def makedict(var):
    return dict(Counter(var))
mod=100000007
k=vary(1)
s=input()
tt=makedict(list(s))
ans=''
for i in tt:
    if tt[i]%k!=0:
        print(-1)
        exit()
    else:
        ans+=i*(tt[i]//k)
print(ans*k)


