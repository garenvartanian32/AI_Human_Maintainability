def main():
    s = input().strip()
    n = int(input())
    result = []
    for ch in s:
        if ch.isupper():
            result.append(chr((ord(ch)-ord('A')+n) % 26 + ord('A')))
        elif ch.islower():
            result.append(chr((ord(ch)-ord('a')+n) % 26 + ord('a')))
        else:
            result.append(ch)
    print(''.join(result))

main()