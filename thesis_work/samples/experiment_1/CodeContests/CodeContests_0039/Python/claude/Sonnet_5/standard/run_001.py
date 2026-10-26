def main():
    n = int(input())
    result = []
    def rec(s, maxc):
        if len(s) == n:
            result.append(''.join(chr(ord('a')+c) for c in s))
            return
        for c in range(maxc+2):
            rec(s+[c], max(maxc, c))
    rec([], -1)
    print(len(result))
    print('\n'.join(result))

main()