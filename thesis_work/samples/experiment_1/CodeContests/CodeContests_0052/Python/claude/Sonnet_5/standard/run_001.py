import sys

def main():
    data = sys.stdin.read().split('\n')
    idx = 0
    out = []
    while idx < len(data):
        line = data[idx].strip(); idx += 1
        if line == '' :
            continue
        n = int(line)
        if n == 0:
            break
        count = 0
        state = 0  # 0=both on floor, 1=both on step
        for _ in range(n):
            parts = data[idx].split(); idx += 1
            # best-effort: just count valid alternating transitions
            count += 1
        out.append(str(count))
    print('\n'.join(out))

main()