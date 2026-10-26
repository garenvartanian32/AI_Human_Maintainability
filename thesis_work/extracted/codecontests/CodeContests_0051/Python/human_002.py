def proc(n):
    new = []
    n = str(n)
    for i in range(1, len(n)):
        new.append(int(n[:i]) * int(n[i:]))
    return max(new)

for _ in range(int(input())):
    ans = 0
    n = int(input())
    while(n > 9):
        n = proc(n)
        ans += 1
    print(ans)
