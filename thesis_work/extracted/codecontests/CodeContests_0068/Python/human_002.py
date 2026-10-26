k = int(input())
s = input()
if not all([s.count(ch) % k == 0 for ch in set(s)]):
    print(-1)
else:
    l = [ch * (s.count(ch) // k) for ch in set(s)] * k
    print(''.join(l))