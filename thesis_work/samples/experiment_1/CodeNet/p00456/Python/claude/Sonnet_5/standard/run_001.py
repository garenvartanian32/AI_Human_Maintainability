def main():
    vals = [int(input()) for _ in range(20)]
    w = sorted(vals[:10], reverse=True)[:3]
    k = sorted(vals[10:], reverse=True)[:3]
    print(sum(w), sum(k))

main()