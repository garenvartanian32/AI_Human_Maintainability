from collections import Counter

def search(lst):
    counts = Counter(lst)
    valid = [value for value, frequency in counts.items() if frequency >= value]
    return max(valid, default=-1)