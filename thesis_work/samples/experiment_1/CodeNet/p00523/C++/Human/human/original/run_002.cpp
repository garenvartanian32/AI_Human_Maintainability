#include<iostream>
#include<cstdio>
#include<algorithm>
using namespace std;
typedef long long int ll;
int N;
ll A[200000];
ll AA[200000];
int l = 0;
int r = 1;
int LL, RR, CC;
int main()
{
	int i, j, k;
	ll ans = 0;
	ll sum = 0;
	ll sum1;
	ll sum2;
	scanf("%d", &N);
	for (i = 0; i < N; i++){
		scanf("%lld", &A[i]);
		A[i + N] = A[i];
	}
	AA[0] = A[0];
	for (i = 1; i < 2 * N; i++)AA[i] = AA[i - 1] + A[i];
	while (l < N){
		if (l != 0)sum = AA[r-1] - AA[l - 1];
		else sum = AA[r-1];
		//printf("%d %d %lld\n", l, r,sum);
		if (sum > AA[N - 1] / 3){
			l++;
		}
		else{
			LL = r; RR = N + l;
			while (RR - LL>1){
				CC = (LL + RR) / 2;
				if (AA[CC] - AA[r-1] < sum)LL = CC;
				else RR = CC;
			}
			LL--;
			if (LL < 0)LL = 0;
			while (sum > AA[LL] - AA[r-1])LL++;
			sum1 = AA[LL] - AA[r-1];
			sum2 = AA[N + l - 1] - AA[LL];
			//printf(":%d %lld %lld\n", LL, sum1, sum2);
			if (sum <= sum1&&sum <= sum2){
				ans = max(ans, sum); r++;
			}
			else l++;
		}
	}
	printf("%lld\n", ans);
	return 0;
}