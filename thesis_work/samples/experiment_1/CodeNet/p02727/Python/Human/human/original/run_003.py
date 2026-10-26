x, y, a, b, c = map(int, input().split())
P = list(map(int, input().split()))
Q = list(map(int, input().split()))
R = list(map(int, input().split()))

P = sorted(P, reverse=True)
Q = sorted(Q, reverse=True)
R = sorted(R, reverse=True)

PQ_eat = P[:x] + Q[:y]
PQ_eat = sorted(PQ_eat)

r_now = 0
ans = sum(PQ_eat)
# print(PQ_eat)
# print(R)
for i in range(len(PQ_eat)):
    if r_now >= len(R):
        break
    if PQ_eat[i] < R[r_now]:
        ans -= PQ_eat[i]
        ans += R[r_now]
        r_now += 1
    else:
        break
print(ans)