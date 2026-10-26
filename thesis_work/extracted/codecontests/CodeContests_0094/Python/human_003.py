from collections import Counter

n = int(input())
s = input()
c = Counter(zip(s, s[1:]))
print(''.join(c.most_common()[0][0]))