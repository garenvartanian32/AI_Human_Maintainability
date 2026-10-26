import sys

def char_to_idx(c):
    if c.islower():
        return ord(c) - ord('a')
    else:
        return 26 + ord(c) - ord('A')

def idx_to_char(i):
    i %= 52
    if i < 26:
        return chr(ord('a') + i)
    else:
        return chr(ord('A') + i - 26)

def main():
    data = sys.stdin.read().split('\n')
    idx = 0
    out = []
    while idx < len(data):
        line = data[idx].strip()
        idx += 1
        if line == '':
            continue
        n = int(line)
        if n == 0:
            break
        keys = list(map(int, data[idx].split()))
        idx += 1
        s = data[idx].strip()
        idx += 1
        result = []
        for i, c in enumerate(s):
            k = keys[i % n]
            orig_idx = char_to_idx(c)
            dec_idx = (orig_idx - k) % 52
            result.append(idx_to_char(dec_idx))
        out.append(''.join(result))
    print('\n'.join(out))

main()