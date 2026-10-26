N = int(input())
A = list(map(int, input().split()))
B = list(map(int, input().split()))

largeA = 0
largeB = 0
for i in range(N):
  d = A[i] - B[i]
  if d > 0:
    largeA += d
  elif d < 0:
    d = -d
    # 奇数の差をつぶす
    if d % 2 == 1:
      d = -1
      B[i] -= 1
    largeB += d

dsum = sum(B) - sum(A)
if dsum >= 0 and largeB//2 <= dsum and largeA <= dsum:
  print('Yes')
else:
  print('No')