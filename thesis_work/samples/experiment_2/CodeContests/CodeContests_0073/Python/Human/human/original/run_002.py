import sys

def getAscendingPattern(pattern):
    pattern = list(map(int,pattern.split("+")))
    pattern.sort()
    
    ascendingPattern = "+".join(list(map(str,pattern)))

    return ascendingPattern

sum_pattern = input().strip()

result = getAscendingPattern(sum_pattern)
print(result)