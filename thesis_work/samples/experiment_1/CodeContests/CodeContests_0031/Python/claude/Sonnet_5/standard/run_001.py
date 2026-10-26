import sys

def main():
    data = sys.stdin.read().split('\n')
    idx = 0
    m, n = map(int, data[idx].split()); idx += 1
    free = [(1, m)]  # list of (start, length) free blocks, in order
    blocks = {}  # id -> (start, length) or None if erased
    next_id = 1
    out = []
    for _ in range(n):
        parts = data[idx].split(); idx += 1
        if parts[0] == 'alloc':
            need = int(parts[1])
            placed = False
            for i, (s, l) in enumerate(free):
                if l >= need:
                    blocks[next_id] = (s, need)
                    if l == need:
                        free.pop(i)
                    else:
                        free[i] = (s+need, l-need)
                    out.append(str(next_id))
                    next_id += 1
                    placed = True
                    break
            if not placed:
                out.append("NULL")
                next_id += 1
        elif parts[0] == 'erase':
            x = int(parts[1])
            if x in blocks and blocks[x] is not None:
                s, l = blocks[x]
                free.append((s, l))
                free.sort()
                merged = []
                for seg in free:
                    if merged and merged[-1][0]+merged[-1][1] == seg[0]:
                        merged[-1] = (merged[-1][0], merged[-1][1]+seg[1])
                    else:
                        merged.append(seg)
                free = merged
                blocks[x] = None
            else:
                out.append("ILLEGAL_ERASE_ARGUMENT")
        else:  # defragment
            occupied = sorted((s,l,bid) for bid,(s,l) in blocks.items() if blocks[bid] is not None)
            pos = 1
            newblocks = {}
            for s,l,bid in occupied:
                newblocks[bid] = (pos, l)
                pos += l
            blocks = newblocks
            free = [(pos, m-pos+1)] if pos <= m else []
    print('\n'.join(out))

main()