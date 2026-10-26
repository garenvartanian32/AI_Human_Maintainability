from collections import defaultdict
from datetime import date

def is_correct(s):
    if s.count('-') != 2 or s[2] != '-' or s[5] != '-':
        return False
    dd, mm, yyyy = map(int, s.split('-'))
    if not (2012 < yyyy < 2016 and 0 < mm < 13):
        return False
    try:
        date(yyyy, mm, dd)
        return True
    except:
        return False

s, d = input(), defaultdict(int)

for i in range(len(s) - 9):
    d[s[i:i+10]] += 1

for s, k in sorted(d.items(), key=lambda x: -x[1]):
    if is_correct(s):
        print(s)
        break