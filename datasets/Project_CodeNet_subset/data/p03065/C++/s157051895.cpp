#include <bits/stdc++.h>

using namespace std;

#define REP(i,n) for(ll (i) = (0);(i) < (n);++i)
#define REV(i,n) for(ll (i) = (n) - 1;(i) >= 0;--i)
#define PB push_back
#define EB emplace_back
#define MP make_pair
#define FI first
#define SE second
#define SHOW1d(v,n) {REP(WW,n)cerr << v[WW] << ' ';cerr << endl << endl;}
#define SHOW2d(v,WW,HH) {REP(W_,WW){REP(H_,HH)cerr << v[W_][H_] << ' ';cerr << endl;}cerr << endl;}
#define ALL(v) v.begin(),v.end()
#define Decimal fixed<<setprecision(20)
#define INF 1000000000
#define LLINF 1000000000000000000LL
#define MOD 998244353

typedef long long ll;
typedef pair<ll,ll> P;

vector<int> makePrimeList(){
	int range = 1000000;
	vector<int> ret;
	vector<bool> notp(range);

	notp[0] = notp[1] = true;
	for(int i = 2;i < range;i++){
		if(!notp[i]){
			ret.PB(i);
			for(int j = 2 * i;j < range;j += i){
				notp[j] = true;
			}
		}
	}

	return ret;
}

void check(vector<ll> v, vector<ll> &ans, ll now){
	bool flag = true;
	int n = v.size();
	REP(i, n)if(v[i] % now)flag = false;
	if(flag)ans.PB(now);
}

int main(){
	cin.tie(0);cout.tie(0);ios::sync_with_stdio(false);

	ll n;cin >> n;
	vector<ll> v(n+1);
	REV(i, n+1)cin >> v[i];

	auto p = makePrimeList();

	vector<ll> ans;
	for(ll i = 0;p[i] <= n;i++){
		if(v[0] % p[i] != 0)continue;
		vector<ll> now(p[i]-1, 0);
		for(int j = 1;j <= n;j++){
			now[(j-1)%(p[i]-1)] += v[j];
		}
		bool flag = true;
		REP(j, p[i]-1){
			if(abs(now[j]) % p[i] != 0)flag = false;
		}
		if(flag)ans.PB(p[i]);
	}

	REP(i,n+1)v[i] = abs(v[i]);
	REP(i, p.size()){
		if(p[i] > n && v[n] % p[i] == 0){
			check(v, ans, p[i]);
		}
		while(v[n] != 0 && v[n] % p[i] == 0)v[n] /= p[i];
	}

	if(v[n] > 1){
		check(v, ans, v[n]);
	}

	REP(i, ans.size()){
		cout << ans[i] << endl;
	}

	return 0;
}
