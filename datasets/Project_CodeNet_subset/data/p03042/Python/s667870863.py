s = raw_input()
print {(0,0):'NA', (0,1): 'YYMM', (1,0): 'MMYY', (1,1):'AMBIGUOUS'}[tuple(map(int,list(tuple([(0<int(s[2*i:2*i+2]) <= 12)	for i in range(2)]))))]