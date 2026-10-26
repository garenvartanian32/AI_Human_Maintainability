n = int(input())
for i in range(n) :
    list_m = list(input())
    list_large_m = sorted(list_m, reverse = True)
    list_small_m = sorted(list_m)
    large_m = "".join(list_large_m)
    small_m = "".join(list_small_m)
    print(int(large_m) - int(small_m))
