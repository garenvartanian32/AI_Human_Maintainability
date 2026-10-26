def bubble_sort(item_list):
    length = len(item_list)
    count = 0

    for lp in range(length):
        for lp2 in range(length - lp - 1):
            if item_list[lp2] > item_list[lp2 + 1]:
                tmp = item_list[lp2]
                item_list[lp2] = item_list[lp2 + 1]
                item_list[lp2 + 1] = tmp
                count += 1

    return count


while True:

    input_count = int(input())

    if input_count == 0:
        break

    data = [int(input()) for _ in range(input_count)]

    result = bubble_sort(data)
    print(result)

