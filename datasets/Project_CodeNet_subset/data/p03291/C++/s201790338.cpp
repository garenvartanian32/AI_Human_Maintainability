#include <bits/stdc++.h>

using namespace std;

using ll = long long;
using ld = long double;

using vi = vector<int>;
using vvi = vector<vi>;
using vll = vector<ll>;
using vvll = vector<vll>;
using vb = vector<bool>;
using vs = vector<string>;

typedef pair<ll, ll> P;

#define bit(n) (1LL << (n))

//#define int long long

#define all(v) v.begin(), v.end()
#define sortAl(v) sort(all(v))
#define sortAlr(v)          \
  sort(v.begin(), v.end()); \
  reverse(v.begin(), v.end())

#define rep(i, n) for (ll i = 0; i < n; i++)
#define REP(i, n) for (ll i = 1; i < n; i++)

#define FOR(i, a, b) for (ll i = (a); i < (b); i++)
#define FORm(i, m) for (auto i = m.begin(); i != m.end(); i++)

template <class T>
inline void chmax(T &a, T b) {
  a = std::max(a, b);
}
template <class T>
inline void chmin(T &a, T b) {
  a = std::min(a, b);
}

#define mod (ll)(1e9 + 7)
#define INF LLONG_MAX

signed main() {
  cin.tie(0);
  ios::sync_with_stdio(false);
  cout << fixed << setprecision(20);

  string s;
  cin >> s;

  ll n = s.size();

  vvll dp(4, vll(n));

  if (s[0] == '?') {
    dp[0][0] = 3;
  } else {
    dp[0][0] = 1;
  }

  if (s[0] == 'A' || s[0] == '?') {
    dp[1][0] = 1;
  }

  REP(i, n) {
    // 1こ前のdpを引き継ぎ
    rep(j, 4) {
      // ? なら3倍に
      if (s[i] == '?') {
        dp[j][i] = dp[j][i - 1] * 3;
      } else {
        dp[j][i] = dp[j][i - 1];
      }
    }

    rep(j, 3) {
      if (s[i] == 'A' + j || s[i] == '?') {
        dp[j + 1][i] += dp[j][i - 1];
      }
    }

    rep(j, 4) {
      dp[j][i] %= mod;
    }
  }

  cout << dp[3][n - 1] << endl;

  return 0;
}
