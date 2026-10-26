H,W = map(int,input().split())
A = []
dame = ["."]*W
for i in range(H):
  temp = str(input())
  temp = list(temp)
  if temp != dame:
    A.append(temp)
#print(A)
S = len(A)
ans = [[] for _ in range(S)]
for j in range(W):
  if all([A[i][j] == "." for i in range(S)]):
    continue
  for t in range(S):
    ans[t].append(A[t][j])
#print(ans)
for i in range(len(ans)):
  temp = "".join(ans[i])
  print(temp)
    