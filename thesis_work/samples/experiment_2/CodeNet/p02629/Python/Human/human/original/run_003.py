import sys
import math

ALPHABET = ["A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"]
result = []

def alphabet_decimal(v):
    quotient = math.floor(v / len(ALPHABET))
    surplus = v % len(ALPHABET)
    quotient -= 1 if surplus == 0 else 0
    surplus = len(ALPHABET) if surplus == 0 else surplus
    result.append(surplus)
    if len(ALPHABET) < quotient:
        alphabet_decimal(quotient)
    elif len(ALPHABET) < v:
        result.append(quotient)
    return "".join([ALPHABET[i - 1] for i in reversed(result)])

N = input()
ans = alphabet_decimal(int(N))
print(ans.lower())
