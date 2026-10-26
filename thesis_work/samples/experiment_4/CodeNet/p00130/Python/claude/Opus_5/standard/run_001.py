import sys

def parse_record(s):
    prev = s[0]
    prev_node = {'char': prev, 'next': None, 'prev': None}
    nodes = {prev: prev_node}
    i = 1
    while i < len(s):
        if s[i:i+2] == '->':
            direction = 'back'
            i += 2
        else:
            direction = 'fwd'
        cur = s[i]
        i += 1
        if cur not in nodes:
            nodes[cur] = {'char': cur, 'next': None, 'prev': None}
        if direction == 'back':
            nodes[prev]['next'] = cur
            nodes[cur]['prev'] = prev
        else:
            nodes[prev]['prev'] = cur
            nodes[cur]['next'] = prev
        prev = cur
    # find head (prev is None)
    head = None
    for ch, node in nodes.items():
        if node['prev'] is None:
            head = ch
            break
    result = []
    cur = head
    while cur is not None:
        result.append(cur)
        cur = nodes[cur]['next']
    return ''.join(result)

def main():
    data = sys.stdin.read().split('\n')
    n = int(data[0].strip())
    out = []
    for i in range(1, n+1):
        s = data[i].strip()
        out.append(parse_record(s))
    print('\n'.join(out))

main()