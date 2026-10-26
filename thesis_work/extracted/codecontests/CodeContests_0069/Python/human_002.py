x0, y0, x1, y1 = map(int, input().split())

n = int(input())
rows = set()
for _ in range(n):
    r, a, b = map(int, input().split())
    rows.update((r, x) for x in range(a, b + 1))

neighbors, visited, moves = [(x0, y0)], {x0, y0}, 0

while neighbors:
    current, neighbors = neighbors, []
    for x, y in current:
        if (x, y) == (x1, y1):
            print(moves)
            exit()
        for xy in (x + 1, y), (x, y + 1), (x - 1, y), (x, y - 1), (x + 1, y + 1), (x - 1, y - 1), (x + 1, y - 1), (x - 1, y + 1):
            if xy in rows and xy not in visited:
                visited.add(xy)
                neighbors.append(xy)
    moves += 1

print(-1)