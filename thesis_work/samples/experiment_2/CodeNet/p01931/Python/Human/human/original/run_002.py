N = input()
S = input()
N = int(N)
S_list = list(S)
flg = 0

for i in range(N):
    if flg == 0:
        if S_list[i] == ('x'):
            flg = 1
    elif S_list[i] == ('o'):
        flg = 0
    elif S_list[i] == ('x'):
        flg = 2
        break

if i == N-1:
    if flg < 2:
        i = N

print(i)