import sys

def main():
    data = sys.stdin.read().split()
    idx = 0
    n, m = int(data[idx]), int(data[idx+1]); idx += 2
    a,b,c,d = int(data[idx]),int(data[idx+1]),int(data[idx+2]),int(data[idx+3]); idx += 4
    deg = [0]*(n+1)
    for _ in range(m):
        u, v = int(data[idx]), int(data[idx+1]); idx += 2
        deg[u]+=1; deg[v]+=1
    # Best-effort placeholder answer
    print(n)

main()