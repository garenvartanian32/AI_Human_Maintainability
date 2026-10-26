s = input()
q = s.count('?')
res = 0
mod = 10**9+7

""" 前処理 """
# ある地点(pivot)まで(から)のA(C),?の数
bp_A,bp_Q,ap_C,ap_Q = [0],[0],[0],[0]
for c in s:
    bp_A.append(bp_A[-1]+(c=='A'))
    bp_Q.append(bp_Q[-1]+(c=='?'))
for c in s[::-1]:
    ap_C.append(ap_C[-1]+(c=='C'))
    ap_Q.append(ap_Q[-1]+(c=='?'))
# 3の累乗のmod
three = [3]
i = 1
while i<q:
    three.append((three[-1]**2)%mod)
    i *= 2
""" ここまで """

# 3の累乗
def pow(num):
    if num<0:
        return 0
    elif num==0:
        return 1
    else:
        ans = 1
        for i,j in enumerate(three):
            if num&(1<<i):
                ans *= j
                ans %= mod
        return ans

for pivot in range(1,len(s)-1):
    if s[pivot]=='B' or s[pivot]=='?':
        tmp = q - (s[pivot]=='?')
        s1_a,s1_q = bp_A[pivot],bp_Q[pivot]
        s2_c,s2_q = ap_C[-pivot-2],ap_Q[-pivot-2]
        res += (s1_a*s2_c)%mod * pow(tmp)
        res += (s1_a*s2_q)%mod * pow(tmp-1)
        res += (s1_q*s2_c)%mod * pow(tmp-1)
        res += (s1_q*s2_q)%mod * pow(tmp-2)

print(int(res)%mod)
