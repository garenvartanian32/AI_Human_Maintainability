P = 998244353
N = int(input())
A = [int(a) for a in input().split()]
V = 1000001
X = [0] * V
for a in A: X[a] += 1
W = [1] + [pow(i, P-2, P) for i in range(1, V)]
for k in range(1, V):
    for i in range(k*2, V, k):
        W[i] = (W[i] - W[k]) % P

ans = 0
for k in range(1, V):
    s1 = s2 = 0
    for i in range(k, V, k):
        s1 += i * X[i]
        s2 += i * i * X[i]
    ans = (ans + (s1*s1-s2) * W[k]) % P

print(ans * 499122177 % P)