N,M=list(map(int,input().split()))
e_list = [[] for i in range(N)]
e_list2 = []
for i in range(M):
    a,b,d = list(map(int,input().split()))
    a=a-1
    b=b-1
    e_list[a].append([b,d])
    e_list[b].append([a,d])
    e_list2.append([a,b,d])

dist_list = []
import heapq
for i in range(N):
    vi = i
    min_d_list = [10**27 for i in range(N)]
    min_d_list[vi] = 0
    prev_list = [-1 for i in range(N)]
    q = []
    for i in range(N):
        heapq.heappush(q,[min_d_list[i],i])

    while len(q)>0:
        d,v = heapq.heappop(q)
        for e in e_list[v]:
            v1,d1=e
            if min_d_list[v1]>d+d1:
                min_d_list[v1]=d+d1
                prev_list[v1]=v
                heapq.heappush(q,[d+d1,v1])
    dist_list.append(min_d_list)
count=len(e_list2)
for e in e_list2:
    for i in range(N):
        if dist_list[i][e[0]]+e[2]==dist_list[i][e[1]]:
            count-=1
            break

print(count)