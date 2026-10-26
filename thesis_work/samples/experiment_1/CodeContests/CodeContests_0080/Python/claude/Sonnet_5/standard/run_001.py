from collections import Counter

def main():
    a = input().strip()
    b = input().strip()
    c = input().strip()
    ca = Counter(a)
    cb = Counter(b)
    cc = Counter(c)
    def can_form(remaining, word_count):
        for ch, cnt in word_count.items():
            if remaining.get(ch, 0) < cnt:
                return False
        return True
    used = []
    remaining = Counter(a)
    while can_form(remaining, cb) or can_form(remaining, cc):
        if can_form(remaining, cb):
            for ch, cnt in cb.items():
                remaining[ch] -= cnt
            used.append(b)
        elif can_form(remaining, cc):
            for ch, cnt in cc.items():
                remaining[ch] -= cnt
            used.append(c)
    result = ''.join(used)
    for ch, cnt in remaining.items():
        result += ch * cnt
    print(result)

main()