import sys

N = int(input())
S = input()

ans = 0
last_s = "o"
for s in S:
    if last_s == "x" and s == "x":
        print(ans)
        sys.exit()

    ans += 1
    last_s = s

print(ans)
