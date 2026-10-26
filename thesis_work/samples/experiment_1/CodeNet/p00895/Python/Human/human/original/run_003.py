import sys
Dir = [(1, 0), (0, 1), (-1, 0), (0, -1), (1, 1), (-1, 1), (1, -1), (-1, -1)]
def ds(here, didx, lb, ub, field, start, string, spell):
	s = string + field[here[1]][here[0]]
	if s in spell :
		spell[s] -= 1
	else :
		spell[s] = -1
	delta = Dir[didx]
	next = [here[0] + delta[0], here[1]+ delta[1]]
	if next[0] < lb[0]:   next[0] = ub[0]
	elif next[0] > ub[0]: next[0] = lb[0]
	if next[1] < lb[1]:   next[1] = ub[1]
	elif next[1] > ub[1]: next[1] = lb[1]

	if next != start:
		ds(next, didx, lb, ub, field, start, s, spell)

sys.setrecursionlimit(10000)

while 1:
	h,w = map(int,raw_input().split(" "))
	if w == h == 0: break
	lb = (0,0)
	ub = (w-1,h-1)
	spell = dict()
	donut = [[c for c in raw_input()] for _ in range(h)]
	for j in range(h):
		for i in range(w):
			for d in range(8):
				ds([i,j], d, lb, ub, donut, [i,j], "", spell)
	spell_sort = sorted(spell.iteritems(), key = lambda x : (-len(x[0]),x[0],x[1]))
	lenspell = len(spell_sort)
	for i in range(lenspell):
		if len(spell_sort[i][0]) >= 2 and spell_sort[i][1] <= -2:
			print spell_sort[i][0]
			break
	else :
		print 0