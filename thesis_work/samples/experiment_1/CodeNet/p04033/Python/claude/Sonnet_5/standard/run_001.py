def main():
    a, b = map(int, input().split())
    if a > 0:
        print("Positive")
    elif b < 0:
        cnt = b - a + 1
        print("Negative" if cnt % 2 == 1 else "Positive")
    else:
        print("Zero")

main()