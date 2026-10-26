from collections import defaultdict


def find_max_possible_substring(a, a_char_counts, char_counts):
    max_count_of_sub = len(a)
    for char, count in char_counts.items():
        max_count_of_sub = min(max_count_of_sub, a_char_counts[char] // char_counts[char])

    return max_count_of_sub


def get_optimal_count(a, a_orig_char_counts, max_count_of_first, first_char_counts, second_char_counts):
    max = 0
    count_of_first = 0
    count_of_second = 0
    for i in range(max_count_of_first+1):
        a_char_counts = a_orig_char_counts.copy()
        for char, count in first_char_counts.items():
            a_char_counts[char] -= count * i

        remaining_count_of_second = find_max_possible_substring(a, a_char_counts, second_char_counts)

        count = remaining_count_of_second + i
        if max < count:
            max = count
            count_of_first = i
            count_of_second = remaining_count_of_second

    return count_of_first, count_of_second


def get_result(a, a_char_counts, first, max_count_of_first, first_char_counts, second, second_char_counts):
    optimal_count_of_first, optimal_count_of_second = get_optimal_count(a, a_char_counts, max_count_of_first,
                                                                        first_char_counts, second_char_counts)
    result = ""
    result += first * optimal_count_of_first

    for char, count in first_char_counts.items():
        a_char_counts[char] -= count * optimal_count_of_first

    if optimal_count_of_second:
        result += second * optimal_count_of_second
        for char, count in second_char_counts.items():
            a_char_counts[char] -= count * optimal_count_of_second

    for char, count in a_char_counts.items():
        result += char * count

    return result


def find_k(a, b, c):
    a_char_counts = defaultdict(lambda: 0)
    b_char_counts = defaultdict(lambda: 0)
    c_char_counts = defaultdict(lambda: 0)

    for char in a:
        a_char_counts[char] += 1

    for char in b:
        b_char_counts[char] += 1

    for char in c:
        c_char_counts[char] += 1

    max_count_of_b = find_max_possible_substring(a, a_char_counts, b_char_counts)

    max_count_of_c = find_max_possible_substring(a, a_char_counts, c_char_counts)

    if max_count_of_b >= max_count_of_c:
        result = get_result(a, a_char_counts, b, max_count_of_b, b_char_counts, c, c_char_counts)
    else:
        result = get_result(a, a_char_counts, c, max_count_of_c, c_char_counts, b, b_char_counts)

    return result


if __name__ == '__main__':
    a_in = input()
    b_in = input()
    c_in = input()

    print(find_k(a_in, b_in, c_in))
