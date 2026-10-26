from sys import stdin
input=stdin.readline

def D708():
    #used editorial
    t=int(input())
    for _ in range(t):
        n=int(input())
        tags=list(map(int,input().split()))
        s=list(map(int,input().split()))
        dp=[0]*n
        for j in range(1, n):
            for i in range(j - 1, -1, -1):
                if tags[i] != tags[j]:
                    score=abs(s[i]-s[j])
                    dpi,dpj=dp[i],dp[j]
                    dp[i] = max(dp[i], dpj + score)
                    dp[j] = max(dp[j], dpi + score)
        print(max(dp))
    
D708()
    
