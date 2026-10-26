from calendar import isleap
s = []
while True:
    a, b = map(int, input().split())
    if not a:
        break
    s.append([])
    for i in range(a, b + 1):
        if isleap(i):
            s[-1].append(str(i))

print('\n\n'.join(['\n'.join(ss) if ss else 'NA' for ss in s]))