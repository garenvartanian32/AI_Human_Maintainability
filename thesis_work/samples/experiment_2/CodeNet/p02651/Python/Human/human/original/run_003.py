def solve():
  n=int(input())
  a=list(map(int,input().split()))
  s=input()
  base=[]
  for i in range(n-1,-1,-1):
    v=a[i]
    for x in base:
      v=min(v,v^x)
    if v:
      if s[i]=='0':
        base.append(v)
      else:
        return 1
  return 0

def main():
  t=int(input())
  for _ in range(t):
    print(solve())

if __name__ == '__main__':
  main()