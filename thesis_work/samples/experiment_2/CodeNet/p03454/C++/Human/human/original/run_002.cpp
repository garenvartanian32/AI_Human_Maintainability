#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
typedef pair<int,int> pp;
typedef pair<ll,ll> pll;
void read(int& x){ scanf("%d",&x); }
void read(ll& x){ scanf("%lld",&x); }
template<typename T,typename... Args>
void read(T& a,Args&... b){ read(a); read(b...); }
#define all(x) (x).begin(),(x).end()
#define pb push_back
#define eb emplace_back
#define x first
#define y second

ll S;

ll ans;

const ll T = 10000, mod = int(1e9) + 7;
ll Z(ll x){ return (x%mod+mod)%mod; }

ll Pow10[20];

ll Pow(ll a, ll b){
	ll ret=1;
	while(b){
		if(b&1) (ret*=a)%=mod;
		b>>=1; (a*=a)%=mod;
	}
	return ret;
}

ll make1(ll x){
	if(x<=10 && 9*Pow10[x-1] < S/x) return 0;
	return Z(9*Pow(10, x-1) - S/x + 1);
}

pll extgcd(ll a, ll b){
	if(!b) return {1, 0};
	pll tmp = extgcd(b, a%b);
	return {tmp.second, tmp.first-a/b*tmp.second};
}

ll gcd(ll a, ll b){ return b?gcd(b, a%b):a; }

ll make2(ll s, ll lv, ll rv){
	if(s < lv+rv || (lv<=10 && rv<=10 && 9*Pow10[lv-1]*lv + 9*Pow10[rv-1]*rv < s)) return 0;
	ll g = gcd(lv, rv);
	if(s%g) return 0;
	ll X, Y; tie(X, Y) = extgcd(lv, rv);
	if(X*lv+Y*rv<0) X=-X, Y=-Y;
	X *= (s/g); Y *= (s/g);
	
	ll dx=rv/g, dy=-lv/g;
	
	ll l = (1-X)/dx-3, r=(S/lv-X)/dx+2;
	
	ll sx, tx;
	
	while(l+1 < r){
		ll mid=((l+r)>>1);
		ll cx = X+mid*dx;
		ll cy = Y+mid*dy;
		if(1<=cx && (rv>=10 || cy<=9*Pow10[rv-1])) r=mid;
		else l=mid;
	}
	sx = X+dx*r;
	
	l = (1-X)/dx-3, r=(S/lv-X)/dx+2;
	
	while(l+1 < r){
		ll mid=((l+r)>>1);
		ll cx = X+mid*dx;
		ll cy = Y+mid*dy;
		if(1<=cy && (lv>=10 || cx<=9*Pow10[lv-1])) l=mid;
		else r=mid;
	}
	tx = X+dx*l;
	if(tx < sx) return 0;
	return (tx-sx)/dx + 1;
}

ll make3(ll b, ll c, ll r){
	if(r>=c) return 0;
	if(b<T) return 0;
	if((b>=10 || c-r <= 9*Pow10[b-1]) && ((b+1)>=10 || r <= 9*Pow10[b])){
		if(r) return 1;
		return make1(b);
	}
	return 0;
}

int main()
{
	Pow10[0] = 1;
	for(int i=1; i<=15; ++i) Pow10[i] = Pow10[i-1]*10;

	read(S);
	for(ll s=1; s<T; ++s){
		if(S%s == 0) (ans += make1(s))%=mod;
		ll cs = S;
		for(ll t=s+1; cs>0;++t){
			(ans += make2(cs, s, t))%=mod;
			if(t >= 10 || (cs -= t*9*Pow10[t-1]) <= 0) break;
		}
	}
	
	for(ll df=0; df<T; ++df){
		ll tg = S-df;
		if(tg <= 0) break;
		for(ll i=1; i*i<=tg; ++i) if(tg%i == 0){
			ans += make3(i, tg/i, df);
			if(i != tg/i)
				ans += make3(tg/i, i, df);
		}
	}
	
	ans %= mod;
	
	printf("%lld\n", ans);
	return 0;
}
