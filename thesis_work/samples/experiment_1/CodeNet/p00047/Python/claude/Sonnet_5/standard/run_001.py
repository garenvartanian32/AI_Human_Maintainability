import sys

def main():
    pos = 'A'
    for line in sys.stdin:
        line = line.strip()
        if not line:
            continue
        x, y = line.split(',')
        if pos == x:
            pos = y
        elif pos == y:
            pos = x
    print(pos)

main()