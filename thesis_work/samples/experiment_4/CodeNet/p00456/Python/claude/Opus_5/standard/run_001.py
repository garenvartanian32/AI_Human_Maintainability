import sys

def main():
    data = sys.stdin.read().split()
    nums = list(map(int, data))
    w = nums[0:10]
    k = nums[10:20]
    w_score = sum(sorted(w, reverse=True)[:3])
    k_score = sum(sorted(k, reverse=True)[:3])
    print(w_score, k_score)

main()