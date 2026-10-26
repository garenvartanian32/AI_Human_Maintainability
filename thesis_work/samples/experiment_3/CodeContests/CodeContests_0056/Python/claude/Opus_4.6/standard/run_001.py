import sys

def main():
    data = sys.stdin.read().split()
    n = int(data[0])
    a = list(map(int, data[1:1+n]))
    total = sum(a)
    k = max(a)
    while n * k <= 2 * total:
        k += 1
    print(k)

main()