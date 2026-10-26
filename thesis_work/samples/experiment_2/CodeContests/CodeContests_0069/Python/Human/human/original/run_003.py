def approved(coord):
	if(coord[0] in allowed):
		for pair in allowed[coord[0]]:
			if(pair[0] <= coord[1] <= pair[1]):
				return True
	return False


def neighbours(cur):
	candidates = { (cur[0] - 1, cur[1] - 1),
	(cur[0] - 1, cur[1]),
	(cur[0] - 1, cur[1] + 1),
	(cur[0], cur[1] - 1),
	(cur[0], cur[1] + 1),
	(cur[0] + 1, cur[1] - 1),
	(cur[0] + 1, cur[1]),
	(cur[0] + 1, cur[1] + 1)}
	return {i for i in candidates if approved(i)}


x0, y0, x1, y1 = map(int, input().split())
n = int(input())
allowed = {}
for i in range(n):
	r, a, b = map(int, input().split())
	if r not in allowed:
		allowed[r] = [(a,b)]
	else:
		allowed[r].append((a,b))

queue = [(x0, y0)]
visited = {(x0, y0): 0} # contains all where visited = True

steps = 0

while(len(queue) > 0):
	cur = queue.pop(0)
	for nbor in neighbours(cur):
		if(nbor not in visited):
			queue.append(nbor)
			visited[nbor] = visited[cur] + 1

if((x1, y1) in visited):
	print(visited[(x1, y1)])
else:
	print(-1)
