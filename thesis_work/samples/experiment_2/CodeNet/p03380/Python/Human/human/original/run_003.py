n = int(input())
a = [int(i) for i in input().split()]
a.sort()
m = max(a)
b = [abs(i - m / 2) for i in a]
print(m, a[b.index(min(b))])