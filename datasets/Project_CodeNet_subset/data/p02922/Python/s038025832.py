def INT(): return int(input())
def MAP(): return map(int, input().split())
def LIST(): return list(map(int, input().split()))

A, B = MAP()

print(-(-(B-1)//(A-1)))

