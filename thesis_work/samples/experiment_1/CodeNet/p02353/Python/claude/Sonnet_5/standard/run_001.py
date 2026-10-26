import sys

def main():
    data = sys.stdin.buffer.read().split()
    idx = 0
    n = int(data[idx]); idx+=1
    q = int(data[idx]); idx+=1
    size = 1
    while size < n:
        size *= 2
    INF = float('inf')
    seg_sum = [0]*(2*size)
    seg_len = [0]*(2*size)
    lazy = [None]*(2*size)
    for i in range(size):
        seg_len[size+i] = 1
    for i in range(size-1, 0, -1):
        seg_len[i] = seg_len[2*i] + seg_len[2*i+1]

    def apply_val(node, val):
        seg_sum[node] = val * seg_len[node]
        lazy[node] = val

    def push_down(node):
        if lazy[node] is not None:
            apply_val(2*node, lazy[node])
            apply_val(2*node+1, lazy[node])
            lazy[node] = None

    def update(node, node_l, node_r, l, r, val):
        if r <= node_l or node_r <= l:
            return
        if l <= node_l and node_r <= r:
            apply_val(node, val)
            return
        push_down(node)
        mid = (node_l+node_r)//2
        update(2*node, node_l, mid, l, r, val)
        update(2*node+1, mid, node_r, l, r, val)
        seg_sum[node] = seg_sum[2*node] + seg_sum[2*node+1]

    def query(node, node_l, node_r, l, r):
        if r <= node_l or node_r <= l:
            return 0
        if l <= node_l and node_r <= r:
            return seg_sum[node]
        push_down(node)
        mid = (node_l+node_r)//2
        return query(2*node, node_l, mid, l, r) + query(2*node+1, mid, node_r, l, r)

    out = []
    for _ in range(q):
        t = int(data[idx]); idx+=1
        if t == 0:
            s = int(data[idx]); idx+=1
            e = int(data[idx]); idx+=1
            x = int(data[idx]); idx+=1
            update(1, 0, size, s, e+1, x)
        else:
            s = int(data[idx]); idx+=1
            e = int(data[idx]); idx+=1
            out.append(str(query(1, 0, size, s, e+1)))
    print('\n'.join(out))

main()