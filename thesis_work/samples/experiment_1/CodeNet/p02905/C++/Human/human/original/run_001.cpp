#include <bits/stdc++.h>
using namespace std;

typedef long long ll;
ll MOD = 998244353;

typedef long long LL;

template<int p> struct FF {
	LL val;

	FF(const LL x=0) { val = (x % p + p) % p; }

	bool operator==(const FF<p>& other) const { return val == other.val; }
	bool operator!=(const FF<p>& other) const { return val != other.val; }

	FF operator+() const { return val; }
	FF operator-() const { return -val; }

	FF& operator+=(const FF<p>& other) { val = (val + other.val) % p; return *this; }
	FF& operator-=(const FF<p>& other) { *this += -other; return *this; }
	FF& operator*=(const FF<p>& other) { val = (val * other.val) % p; return *this; }
	FF& operator/=(const FF<p>& other) { *this *= other.inv(); return *this; }

	FF operator+(const FF<p>& other) const { return FF(*this) += other; }
	FF operator-(const FF<p>& other) const { return FF(*this) -= other; }
	FF operator*(const FF<p>& other) const { return FF(*this) *= other; }
	FF operator/(const FF<p>& other) const { return FF(*this) /= other; }

	static FF<p> pow(const FF<p>& a, LL b) {
		if (!b) return 1;
		return pow(a * a, b >> 1) * (b & 1 ? a : 1);
	}

	FF<p> pow(const LL b) const { return pow(*this, b); }
	FF<p> inv() const { return pow(p - 2); }
};

template<int p> FF<p> operator+(const LL lhs, const FF<p>& rhs) { return FF<p>(lhs) += rhs; }
template<int p> FF<p> operator-(const LL lhs, const FF<p>& rhs) { return FF<p>(lhs) -= rhs; }
template<int p> FF<p> operator*(const LL lhs, const FF<p>& rhs) { return FF<p>(lhs) *= rhs; }
template<int p> FF<p> operator/(const LL lhs, const FF<p>& rhs) { return FF<p>(lhs) /= rhs; }

typedef FF<998244353> num;

num cnt[1000005];
num sum[1000005];

num tot;

int main(){
	int n; cin >> n;
	
	for (int i = 0; i <= 1000000; i++){
		cnt[i] = 0; sum[i] = 0;
	}

	for (int i = 0; i < n; i++){
		int x; cin >> x; cnt[x] = 1 + cnt[x];
		tot += x;
	}

	for (int i = 1; i <= 1000000; i++){
		for (int j = 1; j * i <= 1000000; j++){
			sum[i] += cnt[i * j] * i * j;
		}
		sum[i] *= sum[i];
	}

	for (int i = 1000000; i >= 1; i--){
		for (int j = 2; j * i <= 1000000; j++){
			sum[i] -= sum[i * j];
		}
	}	

	num res = 0;

	for (int i = 1; i <= 1000000; i++){
		res += (sum[i] / i);
	}
	res -= tot;
	res /= 2;
	cout << res.val << endl;
}
