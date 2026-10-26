N,X = map(int,input().split())

def f(a,b) :
    if a > b :
        a,b = b,a
    if b % a == 0 :
        return 2*(b//a-1)*a + a
    else :
        return 2*(b//a)*a + f(a,b%a)
        
ans = N + f(X,N-X)

print(ans)
