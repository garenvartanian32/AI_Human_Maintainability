if __name__ == '__main__':
    line = list(map(str, input().split()))
    print(' '.join(line))
    n = int(input())
    for i in range(n):
        a, b = map(str, input().split())
        if a == line[0]:
            line[0] = b
        elif a == line[1]:
            line[1] = b
        print(' '.join(line))
