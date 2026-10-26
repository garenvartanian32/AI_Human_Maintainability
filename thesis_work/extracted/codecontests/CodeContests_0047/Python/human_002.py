a=sorted(map(int,input().split()))
print('Yes' if a[-1]==sum(a[:-1]) else 'No')