#!/usr/bin/env python3
import sys

def solve():
    data = sys.stdin.buffer.read().split()
    if not data:
        return
    it = iter(data)
    # Example parsing:
    # n = int(next(it))
    # arr = [int(next(it)) for _ in range(n)]
    # TODO: implement problem logic using `it` tokens
    # For now, echo tokens back:
    print(' '.join(token.decode() for token in data))

if __name__ == "__main__":
    solve()