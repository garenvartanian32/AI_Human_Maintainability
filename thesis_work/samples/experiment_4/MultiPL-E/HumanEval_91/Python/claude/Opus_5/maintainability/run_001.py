import re

def is_bored(S):
    boredom_count = 0
    sentences = re.split(r"[.?!]", S)

    for sentence in sentences:
        cleaned_sentence = sentence.strip()
        starts_with_i = (
            cleaned_sentence == "I"
            or cleaned_sentence.startswith("I ")
        )

        if starts_with_i:
            boredom_count += 1

    return boredom_count