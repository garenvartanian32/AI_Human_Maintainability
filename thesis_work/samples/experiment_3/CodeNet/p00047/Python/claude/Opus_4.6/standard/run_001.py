import sys

def main():
    ball = 'A'
    for line in sys.stdin:
        line = line.strip()
        if not line:
            continue
        x, y = line.split(',')
        if ball == x:
            ball = y
        elif ball == y:
            ball = x
    print(ball)

main()