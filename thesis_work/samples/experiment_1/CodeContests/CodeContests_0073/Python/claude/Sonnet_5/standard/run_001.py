def main():
    s = input().strip()
    digits = [c for c in s if c != '+']
    digits.sort()
    print('+'.join(digits))

main()