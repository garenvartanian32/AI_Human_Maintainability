import sys

def main():
    a, b = map(int, sys.stdin.read().split())
    if a <= 0 <= b:
        print("Zero")
    elif a > 0:
        print("Positive")
    else:
        count = b - a + 1
        print("Positive" if count % 2 == 0 else "Negative")

main()