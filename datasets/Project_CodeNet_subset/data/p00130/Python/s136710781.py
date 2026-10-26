n = input()
for i in xrange(n):
    s = raw_input()
    tr = [s[0]]
    for j in xrange(3,len(s),3):
        if s[j] in tr:
            continue
        if s[j-1]==">":
            tr.append(s[j])
        else:
            tr.insert(0,s[j])
    print "".join(tr)