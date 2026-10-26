class dice:
    def __init__(self, li = [0] * 6):
        self.men = [0] + li
    def move0(self, a, b, c, d):
        self.men[a], self.men[b], self.men[c], self.men[d] = self.men[b], self.men[c], self.men[d], self.men[a]
    def move(self, h):
        if h == "N":
            self.move0(1, 2, 6, 5)
        elif h == "S":
            self.move0(1, 5, 6, 2)
        elif h == "W":
            self.move0(1, 3, 6, 4)
        elif h == "E":
            self.move0(1, 4, 6, 3)
        elif h == "O":
            self.men[1], self.men[6], self.men[2], self.men[5] = self.men[6], self.men[1], self.men[5], self.men[2]
        elif h == "L":
            self.move0(2, 3, 5, 4)
        elif h == "R":
            self.move0(2, 4, 5, 3)
        elif h == "B":
            self.men[3], self.men[4], self.men[2], self.men[5] = self.men[4], self.men[3], self.men[5], self.men[2]
        else:
            print("▲err")
    def move2(self, a, b):
        if self.men[1] != a:
            for i, s in [(2, "N"), (3, "W"), (4, "E"), (5, "S"), (6, "O")]:
                if self.men[i] == a:
                    self.move(s)
                    break
        if self.men[2] != b:
            if b == self.men[3]:
                self.move("L")
            elif b == self.men[4]:
                self.move("R")
            else:
                self.move("B")
n = int(input())
cnt = 0
ans = set()
for _ in range(n):
    li = list(map(int, input().split()))
    SET = set()
    for i in range(6):
        saikoro3 = dice(li)
        if i:
            saikoro3.move("_SWENO"[i])
        SET.add(tuple(saikoro3.men))
        for _ in range(3):
            saikoro3.move("L")
            SET.add(tuple(saikoro3.men))
    ans |= SET
    cnt += len(SET)
print("Yes" if len(ans) == cnt else "No")

