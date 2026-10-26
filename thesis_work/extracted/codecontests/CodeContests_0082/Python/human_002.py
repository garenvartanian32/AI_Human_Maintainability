online = set()
answer = 0

try:
	while True:
		line = input()

		if line[0] == '+' or line[0] == '-':
			name = line[1:]
			if line[0] == '+':
				online.add(name)
			else:
				online.remove(name)
		else:
			answer += len(line.split(':')[1]) * len(online)
except:
	pass

print(answer)
