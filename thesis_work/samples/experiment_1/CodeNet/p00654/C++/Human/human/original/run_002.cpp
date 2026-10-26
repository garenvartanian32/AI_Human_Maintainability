#include<iostream>
#include<cstdio>
#include<cmath>
#include<algorithm>
using namespace std;
typedef long long LL;
#define M 100005
LL gcd(LL a, LL b) {
	return a==0?b:gcd(b%a,a);
}
LL n,a[M],b[M],c[M],t;
int main() {
	while(scanf("%lld", &n)!=EOF){
		if(!n)break;
		int x=0,y=0,m=n*(n+1)/2;
		for(int i=0;i<m;i++){
			scanf("%lld",&t);
			if(t&1)a[x++]=t;
			else b[y++]=t;
		}
		sort(a,a+x);
		sort(b,b+y);
		LL tmp[2]={b[0],b[1]};
		for(int i=0;i<2;i++){
			LL res=gcd(a[0],tmp[i]);
			tmp[i]/=res;
			a[0]/=res;
		}
		LL res=(LL)sqrt(1.0*tmp[0]*tmp[1]);
		printf("%lld\n", res);
		for(int i=0;i<y-1;i++)
			printf("%lld ",b[i]/res);
		printf("%lld\n",b[y-1]/res);
	}
	return 0;
}