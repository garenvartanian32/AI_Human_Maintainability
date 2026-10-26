import math
def plot(x,y):
    print(f'{x:.8f} {y:.8f}')
    
def Koch(n,x1,y1,x2,y2):
    if n==0:
        plot(x1,y1)
        return
    sx=(2*x1+x2)/3
    sy=(2*y1+y2)/3
    tx=(x1+2*x2)/3
    ty=(y1+2*y2)/3
    ux=(tx-sx)*(1/2)-(ty-sy)*(math.sqrt(3)/2)+sx
    uy=(tx-sx)*(math.sqrt(3)/2)+(ty-sy)*(1/2)+sy
    Koch(n-1,x1,y1,sx,sy)
    Koch(n-1,sx,sy,ux,uy)
    Koch(n-1,ux,uy,tx,ty)
    Koch(n-1,tx,ty,x2,y2)
n=int(input())
Koch(n,0,0,100,0)
plot(100,0)
