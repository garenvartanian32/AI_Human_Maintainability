import sys

def main():
    a, b = map(int, sys.stdin.read().split())
    s = (a * b) / 3.305785
    print(f"{s:.6f}")

main()