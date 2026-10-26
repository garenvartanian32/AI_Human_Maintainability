#include<bits/stdc++.h>
using namespace std;
typedef long long ll;
const int mod = 998244353;
inline int add(int a,int b){a+=b;return a>=mod?a-mod:a;}
inline int sub(int a,int b){a-=b;return a<0?a+mod:a;}
inline int mul(int a,int b){return 1ll*a*b%mod;}
inline int qpow(int a,int b){int ret=1;for(;b;b>>=1,a=mul(a,a))if(b&1)ret=mul(ret,a);return ret;}
const int N = 1e7+5;
int n;
int fac[N],ifac[N],pw[N];
inline void init(int n = 1e7){
	fac[0]=ifac[0]=1;for(int i=1;i<=n;i++)fac[i]=mul(fac[i-1],i);
	ifac[n]=qpow(fac[n],mod-2);for(int i=n-1;i;i--)ifac[i]=mul(ifac[i+1],i+1);
	pw[0] = 1;for(int i=1;i<=n;i++)pw[i] = mul(pw[i-1],2);
}
inline int binom(int a,int b){
	return mul(fac[a],mul(ifac[b],ifac[a-b]));
}

int main()
{
	init();
	cin >> n;
	int ans = qpow(3,n);
	for(int i=(n>>1)+1;i<=n;i++){
		ans = sub(ans, mul(2, mul(binom(n,i),pw[n-i])));
	}
	cout << ans << endl;
}
