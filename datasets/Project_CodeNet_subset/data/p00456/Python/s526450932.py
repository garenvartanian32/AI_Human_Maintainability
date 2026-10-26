
w = [int(input()) for i in range(10)]
k = [int(input()) for i in range(10)]

list.sort(w); list.sort(k)

print(sum(w[7:]),sum(k[7:]))
