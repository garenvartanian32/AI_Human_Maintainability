s = input()

s1 = int(s[0]+s[1])
s2 = int(s[2]+s[3])

if 1 <= s1 <= 12:
    if 1 <= s2 <= 12:
        ans = "AMBIGUOUS"
    else:
        ans = "MMYY"
else:
    if 1 <= s2 <= 12:
        ans = "YYMM"
    else:
        ans = "NA"
    
print(ans)