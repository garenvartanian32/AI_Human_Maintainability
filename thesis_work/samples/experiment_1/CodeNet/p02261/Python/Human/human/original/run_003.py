# https://onlinejudge.u-aizu.ac.jp/courses/lesson/1/ALDS1/2/ALDS1_2_C


def BubbleSort(C, N):
    for i in range(N):
        for j in range(N-1, i, -1):
            if int(C[j][1]) < int(C[j-1][1]):
                C[j], C[j-1] = C[j-1], C[j]
    return C


def SelectionSort(C, N):
    for i in range(N):
        minj = i
        for j in range(i, N):
            if int(C[j][1]) < int(C[minj][1]):
                minj = j
        C[i], C[minj] = C[minj], C[i]
    return C


def solve():
    n = int(input())
    l = input().split()
    m = l.copy()

    l = BubbleSort(l, n)
    m = SelectionSort(m, n)

    print(' '.join(l))
    print('Stable')
    print(' '.join(m))
    if l == m:
        print('Stable')
    else:
        print('Not stable')


if __name__ == '__main__':
    solve()

