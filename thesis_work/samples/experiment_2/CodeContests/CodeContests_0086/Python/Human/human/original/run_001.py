# by the authority of GOD     author: manhar singh sachdev #
 
import os,sys
from io import BytesIO, IOBase
 
def solve(n,p,path):
    se, x, fin = [1] * (n + 1), 1, [0] * n
    while x != len(se):
        se[x], jj, curr, inde, vals = 0, 0, [x], [x - 1], [p[x - 1]]
        while jj != len(curr):
            zz = curr[jj]
            for y in path[zz]:
                if se[y]:
                    curr.append(y)
                    inde.append(y-1)
                    vals.append(p[y-1])
                    se[y] = 0
            jj += 1
        inde.sort()
        vals.sort()
        for ind, i in enumerate(inde):
            fin[i] = vals[-ind - 1]
        while x != n + 1 and not se[x]:
            x += 1
    for i in fin:
        print(i, end=' ')
 
def main():
    n, m = map(int, input().split())
    p = list(map(int, input().split()))
    path = [[] for _ in range(n + 1)]
    for _ in range(m):
        u1, v1 = map(int, input().split())
        path[u1].append(v1)
        path[v1].append(u1)
    solve(n,p,path)
 
#Fast IO Region
BUFSIZE = 8192
class FastIO(IOBase):
    newlines = 0
    def __init__(self, file):
        self._fd = file.fileno()
        self.buffer = BytesIO()
        self.writable = "x" in file.mode or "r" not in file.mode
        self.write = self.buffer.write if self.writable else None
    def read(self):
        while True:
            b = os.read(self._fd, max(os.fstat(self._fd).st_size, BUFSIZE))
            if not b:
                break
            ptr = self.buffer.tell()
            self.buffer.seek(0, 2), self.buffer.write(b), self.buffer.seek(ptr)
        self.newlines = 0
        return self.buffer.read()
    def readline(self):
        while self.newlines == 0:
            b = os.read(self._fd, max(os.fstat(self._fd).st_size, BUFSIZE))
            self.newlines = b.count(b"\n") + (not b)
            ptr = self.buffer.tell()
            self.buffer.seek(0, 2), self.buffer.write(b), self.buffer.seek(ptr)
        self.newlines -= 1
        return self.buffer.readline()
    def flush(self):
        if self.writable:
            os.write(self._fd, self.buffer.getvalue())
            self.buffer.truncate(0), self.buffer.seek(0)
class IOWrapper(IOBase):
    def __init__(self, file):
        self.buffer = FastIO(file)
        self.flush = self.buffer.flush
        self.writable = self.buffer.writable
        self.write = lambda s: self.buffer.write(s.encode("ascii"))
        self.read = lambda: self.buffer.read().decode("ascii")
        self.readline = lambda: self.buffer.readline().decode("ascii")
sys.stdin, sys.stdout = IOWrapper(sys.stdin), IOWrapper(sys.stdout)
input = lambda: sys.stdin.readline().rstrip("\r\n")
 
if __name__ == '__main__':
    main()