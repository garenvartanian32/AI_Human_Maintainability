a0 = int(input())
an = [a0]
def f(n):
        if n%2 == 0:
                return n/2
        else:
                return 3*n + 1
frag = 0
while frag == 0:
        an.append(f(an[-1]))
        for i in range(len(an)-1):
                if an[-1] == an[i]:
                        frag = 1
print(len(an))