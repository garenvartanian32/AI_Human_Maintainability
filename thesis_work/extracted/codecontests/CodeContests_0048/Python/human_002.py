EPS = 0.0001

# しきい値を決める際に、ベクトル演算をするのだが、
# line, a,b で計算したときESPの誤差を許容する必要がある


# a, b, はラインの同一側にあるか。　-1, 0, 1
def atSameSide(a, b, line):
    sa = (line[1].real-line[0].real)*(a.imag-line[0].imag) \
         + (line[1].imag-line[0].imag)*(line[0].real-a.real);
    sb = (line[1].real-line[0].real)*(b.imag-line[0].imag) \
         + (line[1].imag-line[0].imag)*(line[0].real-b.real);
    if -EPS <= sa and sa <= EPS: return 0          # a in line
    if -EPS <= sb and sb <= EPS: return 0          # b in line
    if sa*sb >= 0: return 1           # a,b at same side
    return -1;
 
while True:
    try: p = list(map(float, input().split(',')))
    except: break
     
    p1 = complex(p[0], p[1])
    p2 = complex(p[2], p[3])
    p3 = complex(p[4], p[5])
    p4 = complex(p[6], p[7])
    if atSameSide(p2, p4, [p1, p3]) == -1 and \
       atSameSide(p1, p3, [p2, p4]) == -1: print('YES')
    else: print('NO')

