contest = True
if not contest:
    fin = open("in", "r")
inp = input if contest else lambda: fin.readline()[:-1]
read = lambda: tuple(map(int, inp().split()))

def cstr(s):
    d = {}
    for c in s:
        if not c in d:
            d[c] = 1
        else:
            d[c] += 1
    return d

s, a, b = inp(), inp(), inp()
ks, ka, kb = cstr(s), cstr(a), cstr(b)

def suka(ks, kx, minusV, minusK):
    ans = ""
    d = 10**20
    #print("k:", minusK, "|", ", ".join([str(k)+":"+str(kx[k]) for k in kx]))
    for k in kx:
        if not k in ks: return 0
        c = ks[k]
        if k in minusV:
            c = max(c - minusV[k] * minusK, 0)
        #print(ks[k] // c, k, ":", ks[k], minusV[k] if k in minusV else "_", minusK)
        d = min(c // kx[k], d)
    #print(d, ka, b * d)
    return d
def solve(s, ka, kb):
    ks = cstr(s)
    #print("\n---------", ", ".join([str(k)+":"+str(ks[k]) for k in ks]))
    #print("---------", ", ".join([str(k)+":"+str(ka[k]) for k in ka]))
    #print("---------", ", ".join([str(k)+":"+str(kb[k]) for k in kb]))
    da = suka(ks, ka, {}, 0)
    mx = 0
    ans = (-1, -1)
    for dai in range(0, da+1):
        db = suka(ks, kb, ka, dai)
        if dai + db >= mx:
            mx = dai + db
            ans = (dai, db)
    return (mx, ans)

ab = ((b, a, kb, ka), (a, b, ka, kb))
answs = [(solve(s, kb, ka), 0), (solve(s, ka, kb), 1)]
ans = max(answs, key=lambda v: v[0])
ab = ab[ans[1]]
ans = ans[0][1]

for k in ks:
    if k in ab[2]: ks[k] -= ans[0] * ab[2][k]
    if k in ab[3]: ks[k] -= ans[1] * ab[3][k]
print(ans[0] * ab[0] + ans[1] * ab[1] + "".join([str(k * ks[k]) for k in ks]))
