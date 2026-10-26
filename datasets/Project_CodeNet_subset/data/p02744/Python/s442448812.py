N = int(input())

alphabet = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z']

def norm(n, prev_list):
	out_list = []
	for line in prev_list:
		for i in range(len(set(line))+1):
			new_line = line + alphabet[i]
			out_list.append(new_line)
	return out_list

init_line = ['a']

for _ in range(N-1):
	init_line = norm(1, init_line)
for line in init_line:
	print(line)