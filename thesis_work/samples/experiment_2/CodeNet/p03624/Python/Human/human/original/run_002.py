from sys import stdin
import sys

S = stdin.readline().rstrip()

for c in range(ord('a'), ord('z')+1):
    if S.find(chr(c)) == -1:
        print(chr(c))
        sys.exit()

print("None")
