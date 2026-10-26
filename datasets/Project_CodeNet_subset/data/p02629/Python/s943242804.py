N = int(input())

s = 1
while N > 26 ** s:
    N -= 26 ** s
    s += 1

ans = ""
N -= 1
for i in range(s):
    ans += chr(ord("a") + N % 26)
    N = N // 26

print(ans[::-1])