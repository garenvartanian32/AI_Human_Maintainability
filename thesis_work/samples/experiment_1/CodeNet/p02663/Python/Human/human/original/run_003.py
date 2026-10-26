H1,M1,H2,M2,K=(int(x) for x in input().split())
H=H2-1-H1
M=M2+60-M1
T=H*60+M
ans=T-K
print(ans)
