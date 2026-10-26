from collections import Counter

def search(lst):
    frequencies = Counter(lst)
    greatest_valid_value = -1

    for value, frequency in frequencies.items():
        if frequency >= value:
            greatest_valid_value = max(greatest_valid_value, value)

    return greatest_valid_value