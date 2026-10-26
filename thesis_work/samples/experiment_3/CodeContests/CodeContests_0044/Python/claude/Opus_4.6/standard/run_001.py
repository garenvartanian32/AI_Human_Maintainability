import sys

def main():
    a, b = map(int, sys.stdin.read().split())
    print("Odd" if (a % 2 == 1 and b % 2 == 1) else "Even")

main()