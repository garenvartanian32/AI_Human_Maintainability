import sys, heapq

def main():
    data = sys.stdin.read().split()
    idx = 0
    n = int(data[idx]); idx+=1
    d = int(data[idx]); idx+=1
    a = int(data[idx]); idx+=1
    monsters = []
    for _ in range(n):
        x = int(data[idx]); idx+=1
        h = int(data[idx]); idx+=1
        monsters.append((x,h))
    monsters.sort()
    heap = []  # (end_x, amount)
    cumulative = 0
    bombs = 0
    for x, h in monsters:
        while heap and heap[0][0] < x:
            _, amt = heapq.heappop(heap)
            cumulative -= amt
        eff = h - cumulative
        if eff > 0:
            need = (eff + a - 1)//a
            bombs += need
            cumulative += need*a
            end = x + 2*d
            heapq.heappush(heap, (end, need*a))
    print(bombs)

main()