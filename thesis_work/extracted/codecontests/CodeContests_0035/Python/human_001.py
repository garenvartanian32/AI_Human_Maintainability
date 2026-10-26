def is_prime(a):
    return all(a % i for i in range(2, a))


n, k = map(int, input().split())
l = [int(x) for x in input().split()]
if is_prime(k):
    if k in l:
        print(1)
    else:
        print(k)
else:
    ll = []
    for i in range(len(l)):
        if k % l[i] == 0:
            ll.append(l[i])
    print(k // max(ll))
