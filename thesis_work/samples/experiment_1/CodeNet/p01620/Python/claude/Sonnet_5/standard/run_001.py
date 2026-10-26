import sys

def main():
    alphabet = [chr(ord('a')+i) for i in range(26)] + [chr(ord('A')+i) for i in range(26)]
    pos = {c:i for i,c in enumerate(alphabet)}
    data = sys.stdin.read().split('\n')
    idx = 0
    out = []
    while True:
        line = data[idx].strip(); idx += 1
        n = int(line)
        if n == 0:
            break
        keys = list(map(int, data[idx].split())); idx += 1
        s = data[idx].strip(); idx += 1
        result = []
        for i, c in enumerate(s):
            k = keys[i % n]
            p = (pos[c] - k) % 52
            result.append(alphabet[p])
        out.append(''.join(result))
    print('\n'.join(out))

main()