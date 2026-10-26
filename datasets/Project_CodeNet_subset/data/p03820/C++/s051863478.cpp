#include<iostream>
#include<stdio.h>
#include<string.h>
#include<algorithm>
#include<vector>
#define maxn 20005
#define mod 1000000007
using namespace std;
long long n, k;
long long f[maxn];

long long pow(long long x, long long k) {
	if(k < 0) return 1;
	long long res = 1ll;
	while(k) {
		if(k & 1) res = (long long)(res * x) % mod;
		x = (long long)(x * x) % mod;
		k >>= 1;
	}
	return res;
}

int main() {
	f[0] = 1ll;
	scanf("%lld%lld", &n, &k);
	for(int i = 1; i < n; i ++) {
		for(int j = i; j; j --)
			f[j] = (long long)(f[j + 1] + f[j - 1]) % mod;
		f[0] = f[1];
	}
	printf("%lld\n", (long long)f[n - k] * pow(2ll, n - k - 1) % mod);
}