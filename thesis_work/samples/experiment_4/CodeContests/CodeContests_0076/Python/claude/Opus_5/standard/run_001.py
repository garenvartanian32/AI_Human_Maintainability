import sys

def main():
    data = sys.stdin.read().split()
    n = int(data[0])
    w = list(map(int, data[1:1+n]))
    units = [x // 100 for x in w]
    total = sum(units)
    if total % 2 != 0:
        print("NO")
        return
    half = total // 2
    dp = [False] * (half + 1)
    dp[0] = True
    for u in units:
        for s in range(half, u - 1, -1):
            if dp[s - u]:
                dp[s] = True
    print("YES" if dp[half] else "NO")

main()