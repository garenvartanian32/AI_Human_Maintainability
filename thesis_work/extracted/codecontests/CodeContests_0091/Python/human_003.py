"""Ý tưởng, Nằm trên đường tròn tâm là điểm thứ 2 và 2 điểm """
ax, ay, bx, by, cx, cy = input().split()
ax, ay, bx, by, cx, cy = int(ax), int(ay), int(bx), int(by), int(cx), int(cy)

distant_1 = (ax - bx) ** 2 + (ay - by) ** 2
distant_2 = (bx - cx) ** 2 + (by - cy) ** 2

"""Vector AB và BC kiểm tra thẳng hàng"""
abx = bx - ax
aby = by - ay
bcx = cx - bx
bcy = cy - by

"""Không chia được thì nhân, vì số 0 thì không chia được"""
if distant_1 != distant_2:
    print('No')
if distant_1 == distant_2:
    if abx * bcy == aby * bcx:
        print('No')
    else:
        print('Yes')
