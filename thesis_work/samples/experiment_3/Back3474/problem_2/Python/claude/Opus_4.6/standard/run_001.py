from collections import deque

class Solution:
    def finalString(self, s: str) -> str:
        dq = deque()
        normal = True
        for c in s:
            if c == 'i':
                normal = not normal
            else:
                if normal:
                    dq.append(c)
                else:
                    dq.appendleft(c)
        if not normal:
            dq.reverse()
        return ''.join(dq)