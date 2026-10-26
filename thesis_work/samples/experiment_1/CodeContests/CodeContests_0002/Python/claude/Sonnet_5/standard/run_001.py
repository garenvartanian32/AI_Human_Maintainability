def main():
    q = int(input())
    out = []
    for _ in range(q):
        l, r, d = map(int, input().split())
        if d < l:
            out.append(str(d))
        else:
            out.append(str((r // d + 1) * d))
    print('\n'.join(out))

main()