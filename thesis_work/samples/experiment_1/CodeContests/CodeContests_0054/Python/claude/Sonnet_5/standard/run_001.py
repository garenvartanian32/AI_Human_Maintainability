def main():
    n = int(input())
    a = list(map(int, input().split()))
    swaps = 0
    for i in range(n):
        mini = i
        for j in range(i, n):
            if a[j] < a[mini]:
                mini = j
        if mini != i:
            a[i], a[mini] = a[mini], a[i]
            swaps += 1
    print(' '.join(map(str, a)))
    print(swaps)

main()