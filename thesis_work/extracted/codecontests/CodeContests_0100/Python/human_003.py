n = int(input())
f_list = [int("".join(input().split()), 2) for _ in range(n)]
p_list = [[int(x) for x in input().split()] for _ in range(n)]
ans = -(10 ** 10)
for i in range(1, 2 ** 10):
    temp = 0
    for j in range(n):
        temp += p_list[j][bin(i & f_list[j]).count("1")]
    if temp > ans:
        ans = temp
print(ans)