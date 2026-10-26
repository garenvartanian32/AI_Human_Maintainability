#include <bits/stdc++.h>
using namespace std;
using ll = long long;
const ll INF = 1e16;
const ll mod = 1000000007;
#define rep(i, n) for (int i = 0; i < (int)(n); i++)

template<class T> inline bool chmax(T& a, T b) {
  if (a < b) {
    a = b;
    return 1;
  }
  return 0;
}
template<class T> inline bool chmin(T& a, T b) {
  if (a > b) {
    a = b;
    return 1;
  }
  return 0;
}
int ctoi(char c) {
  if (c >= '0' && c <= '9') {
    return c - '0';
  }
  return 0;
}
ll factorial(ll n) {
  if (n == 1) {
    return 1;
  }
  ll ans = factorial(n-1);
  return ans*n;
}
ll gcd(ll a, ll b) {
  if (b == 0) return a;
  return gcd(b, a%b);
}
ll lcm(ll a, ll b) {
  return (a/gcd(a, b))*b;
}
bool is_prime(ll n) {
  for (ll i = 2; i*i <= n; i++) {
    if (n%i == 0) return false;
  }
  return n != 1;
}
ll mod_pow(ll x, ll n, ll mod) {
  if (n == 0) return 1;
  ll res = mod_pow(x*x%mod, n/2, mod);
  if (n & 1) res = res * x % mod;
  return res;
}

int main() {
  ll n;
  cin >> n;
  vector <ll> v(n);
  rep(i, n) cin >> v.at(i);
  sort(v.begin(), v.end());
  ll res1 = v.at(n-1), res2 = v.at(0), tmp = INF;
  if (res1%2 == 0) {
    rep(i, n-1) {
      if (tmp > abs(res1/2-v.at(i))) {
        tmp = abs(res1/2-v.at(i));
        res2 = v.at(i);
      }
    }
  } else {
    rep(i, n-1) {
      if (tmp > abs(res1/2-v.at(i))+abs(res1/2+1-v.at(i))) {
        tmp = abs(res1/2-v.at(i))+abs(res1/2+1-v.at(i));
        res2 = v.at(i);
      }
    }
  }
  cout << res1 << ' ' << res2 << endl;
}