import sys

def main():
    data = sys.stdin.read().split()
    n = int(data[0])
    v = list(map(int, data[1:1+n]))
    total = sum(v) - n*(n+1)//2
    print(total)

main()