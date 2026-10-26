from collections import deque
n, k = map(int, input().split())
onla = []
onlb = []
both = []
for i in range(n):
    a, b, c = map(int, input().split())
    if b == 1 and c == 1:
        both.append(a)
    elif c == 1:
        onlb.append(a)
    elif b == 1:
        onla.append(a)
if len(both) + len(onla) < k or len(both) + len(onlb) < k:
    print(-1)
else:
    onlb.sort()
    onlb = deque(onlb)
    onla.sort()
    onla = deque(onla)
    both.sort()
    both = deque(both)
    al = k
    bob = k
    ans = 0
    while al > 0 or bob > 0:
        if al > 0 and bob > 0:
            if len(onla) == 0 or len(onlb) == 0:
                ans += both[0]
                al -= 1
                bob -= 1
                both.popleft()
            else:
                if len(both) == 0:
                    ans += onla[0] + onlb[0]
                    al -= 1
                    bob -= 1
                    onla.popleft()
                    onlb.popleft()
                else:
                    if onla[0] + onlb[0] <= both[0]:
                        ans += onla[0] + onlb[0]
                        al -= 1
                        bob -= 1
                        onla.popleft()
                        onlb.popleft()
                    else:
                        ans += both[0]
                        al -= 1
                        bob -= 1
                        both.popleft()
        elif bob > 0:
            if both[0] < onlb[0]:
                ans += both[0]
                bob -= 1
                both.popleft()
            else:
                ans += onlb[0]
                bob -= 1
                onlb.popleft()
        elif al > 0:
            if both[0] < onla[0]:
                ans += both[0]
                bob -= 1
                both.popleft()
            else:
                ans += onla[0]
                bob -= 1
                onlb.popleft()
    print(ans)