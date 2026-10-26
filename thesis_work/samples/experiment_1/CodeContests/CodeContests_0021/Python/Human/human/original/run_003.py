a, b = map(int, input().split())
hrs = a
melt = a
while (melt >= b):
    hrs += melt // b
    melt = melt % b + melt // b
print(hrs)