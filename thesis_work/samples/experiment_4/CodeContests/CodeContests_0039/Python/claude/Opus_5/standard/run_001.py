import sys

def main():
    n = int(sys.stdin.read().split()[0])
    buf = []
    out = []

    def bt(max_used):
        if len(buf) == n:
            out.append(''.join(buf))
            return
        for c in range(0, max_used + 2):
            buf.append(chr(ord('a') + c))
            bt(max(max_used, c))
            buf.pop()

    bt(-1)
    print('\n'.join(out))

main()