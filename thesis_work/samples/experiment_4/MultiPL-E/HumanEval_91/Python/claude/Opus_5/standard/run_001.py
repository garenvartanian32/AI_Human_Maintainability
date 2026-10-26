import re

def is_bored(S):
    sentences = re.split(r"[.?!]", S)
    return sum(
        1
        for sentence in sentences
        if sentence.strip() == "I" or sentence.strip().startswith("I ")
    )