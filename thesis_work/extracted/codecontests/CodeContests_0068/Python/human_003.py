count_arr = [0 for i in range(26)]
ans = []
k = int(input())
s = input()
for i in s:
    count_arr[ord(i) - ord('a')] += 1
for i in range(26):
    if count_arr[i]%k != 0:
        print(-1)
        exit(0)
    elif count_arr[i]:
        ans.append((chr(ord('a') + i)) * (count_arr[i]//k))
print(*ans * k,sep = '')