import sys

def main():
    data = sys.stdin.read().split('\n')
    first = data[0].split()
    t = int(first[0]); m = int(first[1])
    mem = [0] * m
    active = set()
    next_id = 1
    out = []
    for i in range(1, t + 1):
        parts = data[i].split()
        cmd = parts[0]
        if cmd == 'alloc':
            n = int(parts[1])
            found = -1
            run = 0
            for j in range(m):
                if mem[j] == 0:
                    run += 1
                    if run == n:
                        found = j - n + 1
                        break
                else:
                    run = 0
            if found == -1:
                out.append("NULL")
            else:
                for j in range(found, found + n):
                    mem[j] = next_id
                active.add(next_id)
                out.append(str(next_id))
                next_id += 1
        elif cmd == 'erase':
            x = int(parts[1])
            if x in active:
                for j in range(m):
                    if mem[j] == x:
                        mem[j] = 0
                active.discard(x)
            else:
                out.append("ILLEGAL_ERASE_ARGUMENT")
        elif cmd == 'defragment':
            vals = [v for v in mem if v != 0]
            for j in range(m):
                mem[j] = vals[j] if j < len(vals) else 0
    print('\n'.join(out))

main()