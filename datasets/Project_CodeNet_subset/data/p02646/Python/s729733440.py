def resolve():
    A, V = [int(i) for i in input().split()]
    B, W = [int(i) for i in input().split()]
    T = int(input())
    if V <= W:
        print("NO")
    elif (V - W) * T < abs(A - B):
        print("NO")
    else:
        print("YES")

resolve()