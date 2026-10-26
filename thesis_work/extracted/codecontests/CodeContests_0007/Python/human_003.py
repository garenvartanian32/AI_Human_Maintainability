t=int(input())
for _ in range(t):
    n,l,r=map(int, input().split())
    cycle_size = 2 * (n - 1)
    cycle_start = 1
    cycle_number = 1
    # while True:
    #     if cycle_start + cycle_size >= l:
    #         break
    #     cycle_start += cycle_size
    #     cycle_number += 1
    #     cycle_size -= 2
    # print [l,r]

    while cycle_start + cycle_size <= l and cycle_number != n:
        cycle_start += cycle_size
        cycle_number += 1
        cycle_size -= 2

    # print('cycle_start, cycle_size', cycle_start, cycle_size)
    ans = []
    while l <= r:
        # print('l, cycle_start, cycle_size', l, cycle_start, cycle_size)
        if (l - cycle_start) & 1:
            # print('cycle_size', cycle_size)
            if not cycle_size:
                ans.append(1)
            else:
                ans.append(cycle_number + (l - cycle_start + 1) // 2)
        else:
            # print('cycle_size', cycle_size)
            if not cycle_size:
                ans.append(1)
            else:
                ans.append(cycle_number)
        # print('ans', ans)
        # if (l - cycle_start) // 2
        l += 1
        if (l - cycle_start) >= cycle_size:
            cycle_start += cycle_size
            cycle_size -= 2
            cycle_number += 1
    print(' '.join(str(x) for x in ans))
