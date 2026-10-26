n = int(input())
s = [input() for i in range(n)]
ans = ['AC', 'WA', 'TLE', 'RE']
for i in ans:
    print(i, 'x', s.count(i))
