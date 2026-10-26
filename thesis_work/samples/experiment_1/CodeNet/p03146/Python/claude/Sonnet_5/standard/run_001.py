def main():
    s = int(input())
    seen = {}
    a = s
    i = 1
    seen[a] = i
    while True:
        if a % 2 == 0:
            a = a // 2
        else:
            a = 3*a + 1
        i += 1
        if a in seen:
            print(i)
            return
        seen[a] = i

main()