import sys

def main():
    data = sys.stdin.read().split()
    n = int(data[0])
    a = list(map(int, data[1:1+n]))
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