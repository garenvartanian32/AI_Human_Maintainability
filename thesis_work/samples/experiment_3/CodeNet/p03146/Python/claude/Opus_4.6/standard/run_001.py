import sys

def main():
    s = int(sys.stdin.read().strip())
    seen = {}
    seq = []
    cur = s
    i = 1
    seen[cur] = 1
    seq.append(cur)
    while True:
        if cur % 2 == 0:
            cur = cur // 2
        else:
            cur = 3 * cur + 1
        i += 1
        if cur in seen:
            print(i)
            return
        seen[cur] = i

main()