import sys

def main():
    a, b = map(int, sys.stdin.read().split())
    total = a
    leftover = a
    while leftover >= b:
        new = leftover // b
        total += new
        leftover = leftover % b + new
    print(total)

main()