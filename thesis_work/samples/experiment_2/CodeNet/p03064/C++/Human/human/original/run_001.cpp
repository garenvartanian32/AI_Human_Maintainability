#include <bits/stdc++.h>

typedef long long ll;

using namespace std;

#define swap(a, b)    do { typeof(a) __tmp = (a); (a) = (b); (b) = __tmp; } while(0)
#define repd(i, a, b) for(typeof(b) i = a; i < (b); ++i)
#define rep(i, n)     repd(i, 0, n)
#define dump(x)       cerr << #x << " = " << (x) << endl
#define debug(x)      cerr << "L" << __LINE__ << ": " << #x << " = " << (x) << endl
#define YesNo(x) cout << ((x) ? "Yes" : "No") << endl
#define YESNO(x) cout << ((x) ? "YES" : "NO") << endl
                
const ll moda = 998244353;
ll log10(ll a) {ll ret = 0;while(a != 0) {a /= 10;++ret;}return ret;}
ll sub(ll a, ll b){
	a %= moda;
	b %= moda;
	if(a < b) a += moda;
	return (a - b) % moda;
}

class subsetsum {
public:
	ll n;
	vector<ll> a;
	vector<vector<ll>> table;
	ll mod;

	subsetsum() : mod(0x7FFFFFFFFFFFFFFF) {}
	subsetsum(ll n0) : mod(0x7FFFFFFFFFFFFFFF) {init(n0);}
	subsetsum(ll n0, ll mod0) : mod(mod0){init(n0);}

	void init(ll n0) {
		n = n0;
		a = vector<ll>(n, 0);
		table = vector<vector<ll>>(n + 1);
	}
	
	bool is_normal() {
		if(n == 0) return false;
		if( a.size() != n || table.size() != n + 1 ) return false;
		return true;
	}

	/*
	 *  各要素を RGB の3色に塗り分け、その色を i 個目まで決めた時に、
	 *  R を塗った要素の合計が j であるような場合の数
	 *  border 以上、sum 以下の組み合わせを数え上げ
	 */
	ll dp1(ll border, ll sum) {
		if(!is_normal()) {cerr << "dp1: is_normal() is false" << endl;return 0;}

		table = vector<vector<ll>>(n + 1, vector<ll>(sum + 1, 0));
		table[0][0] = 1;
		for(size_t i = 1; i <= sum; ++i) table[0][i] = 0;

		for(size_t i = 0; i < n; ++i) {
			for(size_t j = 0; j <= sum; ++j) {
				table[i + 1][j] = (2 * table[i][j]) % mod ;
				if(j >= a[i]) {
					table[i + 1][j] = (table[i + 1][j] + table[i][j - a[i]]) % mod;
				}
			}
		}

		ll ret = 0;
		repd(i, border, sum+1) {
			ret = (ret + table[n][i]) % mod;
		}

		return ret;
	}

	/*
	 *  各要素を RG の2色に塗り分け、その色を i 個目まで決めた時に、
	 *  R を塗った要素の合計が j であるような場合の数
	 */
	ll dp2(ll sum) {
		if(!is_normal()) {cerr << "dp1: is_normal() is false" << endl;return 0;}

		table = vector<vector<ll>>(n + 1, vector<ll>(sum + 1, 0));
		table[0][0] = 1;
		for(size_t i = 1; i <= sum; ++i) table[0][i] = 0;

		for(size_t i = 0; i < n; ++i) {
			for(size_t j = 0; j <= sum; ++j) {
				table[i + 1][j] = table[i][j];
				if(j >= a[i]) {
					table[i + 1][j] = (table[i + 1][j] + table[i][j - a[i]]) % mod;
				}
			}
		}

		return table[n][sum];
	}
};

int main(int argc, const char* argv[]) {
	ll n, sum = 0;
	cin >> n;
	subsetsum sss(n, moda);
	rep(i, n) {
		cin >> sss.a[i];
		sum += sss.a[i];
	}

	ll u = 1; rep(i, n) u = (u * 3) % moda;
	ll a = sss.dp1((sum + 2 - 1) / 2, sum);
	ll b = ( (sum % 2 == 0) ? sss.dp2(sum / 2) : 0 );

	cout << (sub(u, 3 * a) + 3 * b) % moda << endl;

	return 0;
}
