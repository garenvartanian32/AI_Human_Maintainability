import sys
from typing import Iterator


def solve(x: int) -> Iterator[int]:
    global cum_prev, cum
    end = 0
    it = iter(cum).__next__
    nxt = it() - x
    for start, c in enumerate(cum_prev):
        while (nxt <= c):
            nxt = it() - x
            end += 1
        yield end - start


if __name__ == "__main__":
    N, Q = map(lambda x: int(x), input().split())
    a = list(map(lambda x: int(x), input().split()))
    x = list(map(lambda x: int(x), input().split()))

    cum_prev = [0]
    cum = []
    v = 0
    for elem in a:
        v += elem
        cum_prev.append(v)
        cum.append(v)
    cum_prev.pop()
    cum.append(sys.maxsize)

    print("\n".join(map((lambda t: str(sum(t))), map(solve, x))))

