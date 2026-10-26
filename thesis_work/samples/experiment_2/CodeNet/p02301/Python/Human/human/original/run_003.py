from math import sqrt

def cross(P0, P1, P2):
    x0, y0 = P0; x1, y1 = P1; x2, y2 = P2
    x1 -= x0; x2 -= x0
    y1 -= y0; y2 -= y0
    return x1*y2 - x2*y1

def dot(P0, P1, P2):
    x0, y0 = P0; x1, y1 = P1; x2, y2 = P2
    x1 -= x0; x2 -= x0
    y1 -= y0; y2 -= y0
    return x1*x2 + y1*y2

def dist2(P0, P1):
    x0, y0 = P0; x1, y1 = P1
    return (x1 - x0)**2 + (y1 - y0)**2

def convex_hull(PS):
    QS = []
    n = len(PS)
    if n==1:
        return PS[:]
    for P in PS:
        while len(QS)>1 and cross(QS[-1], QS[-2], P) > 0:
            QS.pop()
        QS.append(P)
    k = len(QS)
    RS = reversed(PS); next(RS)
    for P in RS:
        while len(QS)>k and cross(QS[-1], QS[-2], P) > 0:
            QS.pop()
        QS.append(P)
    return QS

def cross4(S0, S1, T0, T1):
    x0, y0 = S0; x1, y1 = S1
    X0, Y0 = T0; X1, Y1 = T1
    return (x1-x0)*(Y1-Y0) - (y1-y0)*(X1-X0)
def calipers(PS):
    QS = convex_hull(PS)
    n = len(QS)
    if n == 2:
        return sqrt(dist2(*QS))
    i = j = 0
    for k in range(n):
        if QS[k] < QS[i]: i = k
        if QS[j] < QS[k]: j = k
    res = 0
    si = i; sj = j
    while i != sj or j != si:
        res = max(res, dist2(QS[i], QS[j]))
        if cross4(QS[i], QS[i-n+1], QS[j], QS[j-n+1]) < 0:
            i = (i + 1) % n
        else:
            j = (j + 1) % n
    return sqrt(res)

n = input()
PS = [map(float, raw_input().split()) for i in range(n)]
PS.sort()
print("%.09f" % calipers(PS))