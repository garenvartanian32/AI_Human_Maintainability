

def main():
    cx1, cy1, r1 = map(int, input().split())
    cx2, cy2, r2 = map(int, input().split())
    d = pow((cx2 -cx1)**2 + (cy2 -cy1)**2, 0.5) 

    if d > r1 + r2:
        # 4(not cross)
        print("4")
    elif d == r1 + r2:
        # 3(circumscribed)
        print("3")
    elif abs(r1 - r2) < d and d < r1 + r2:
        # 2(intersec)
        print("2")
    elif d == abs(r1 - r2):
        # 1(inscribed)
        print("1")
    elif d < abs(r1 - r2):
        # 0(includes another)
        print("0")


if __name__=="__main__":
    main()

