import sys

def main():
    lines = sys.stdin.read().split('\n')
    s = lines[0].strip()
    n = int(lines[1].strip())
    out = []
    for c in s:
        base = c.lower()
        rank = ord(base) - ord('a') + 1
        if rank <= n:
            out.append(base.upper())
        else:
            out.append(base)
    print(''.join(out))

main()