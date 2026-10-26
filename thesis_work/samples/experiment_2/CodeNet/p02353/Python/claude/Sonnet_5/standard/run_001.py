import sys

def main():
    input_data = sys.stdin.buffer.read().split()
    idx = 0
    n = int(input_data[idx]); q = int(input_data[idx+1]); idx += 2

    size = 1
    while size < n:
        size *= 2

    sum_tree = [0] * (2 * size)
    lazy = [None] * (2 * size)

    def push_down(node, node_len):
        if lazy[node] is not None:
            val = lazy[node]
            left = 2*node
            right = 2*node+1
            half = node_len // 2
            sum_tree[left] = val * half
            sum_tree[right] = val * half
            lazy[left] = val
            lazy[right] = val
            lazy[node] = None

    def update(node, node_l, node_r, s, t, x):
        if t < node_l or node_r < s:
            return
        if s <= node_l and node_r <= t:
            sum_tree[node] = x * (node_r - node_l + 1)
            lazy[node] = x
            return
        push_down(node, node_r - node_l + 1)
        mid = (node_l + node_r) // 2
        update(2*node, node_l, mid, s, t, x)
        update(2*node+1, mid+1, node_r, s, t, x)
        sum_tree[node] = sum_tree[2*node] + sum_tree[2*node+1]

    def query(node, node_l, node_r, s, t):
        if t < node_l or node_r < s:
            return 0
        if s <= node_l and node_r <= t:
            return sum_tree[node]
        push_down(node, node_r - node_l + 1)
        mid = (node_l + node_r) // 2
        return query(2*node, node_l, mid, s, t) + query(2*node+1, mid+1, node_r, s, t)

    out = []
    for _ in range(q):
        typ = int(input_data[idx]); idx += 1
        if typ == 0:
            s = int(input_data[idx]); t = int(input_data[idx+1]); x = int(input_data[idx+2]); idx += 3
            update(1, 0, size-1, s, t, x)
        else:
            s = int(input_data[idx]); t = int(input_data[idx+1]); idx += 2
            out.append(str(query(1, 0, size-1, s, t)))

    print('\n'.join(out))

main()