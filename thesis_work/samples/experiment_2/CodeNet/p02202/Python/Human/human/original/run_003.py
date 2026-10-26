N = int(input())
V = [int(i) for i in input().split()]

print(sum(V) - N * (N + 1) // 2)
