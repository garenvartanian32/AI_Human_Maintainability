import sys

def main():
    s = sys.stdin.read().strip()
    nums = sorted(s.split('+'), key=int)
    print('+'.join(nums))

main()