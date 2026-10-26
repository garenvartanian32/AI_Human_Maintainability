import copy

def bubble_sort(data_bubble):
    for i in range(len(data_bubble)):
        for j in range(len(data_bubble)-1, i, -1):
            if data_bubble[j][1] < data_bubble[j-1][1]:
                data_bubble[j], data_bubble[j-1] = data_bubble[j-1], data_bubble[j]
    return data_bubble


def selection_sort(data_selec):
    for i in range(len(data_selec)):
        minj = i
        for j in range(i, len(data_selec)):
            if data_selec[j][1] < data_selec[minj][1]:
                minj = j
        data_selec[i], data_selec[minj] = data_selec[minj], data_selec[i]
    return data_selec


def set_list(data):
    base_list = [[], [], [], [], [], [], [], [], []]
    for elem in data:
        base_list[int(elem[1]) - 1].append(elem)
    return base_list


def is_stable(base, compare):
    base_list = set_list(base)
    compare_list = set_list(compare)
    for i, num in enumerate(base_list):
        for j, key in enumerate(num):
            if key != compare_list[i][j]:
                return 'Not stable'
    return 'Stable'


_ = int(input())
data = list(input().split())
bubble_list = copy.copy(data)
bubble_list = bubble_sort(bubble_list)
print(*bubble_list)
print(is_stable(data, bubble_list))
selection_list = copy.copy(data)
selection_list = selection_sort(selection_list)
print(*selection_list)
print(is_stable(data, selection_list))

