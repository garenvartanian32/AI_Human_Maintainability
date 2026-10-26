#include <bits/stdc++.h>
#define int long long
#define uint unsigned int
#define REP(i, n) for (int i = 0;i < (n);++i)
#define FOR(i, a, b) for (int i=(a);i<(b);++i)
#define FORR(i, a, b) for (int i=(a);i>=(b);--i)
#define ALL(a) (a).begin(), (a).end()
#define RALL(a) (a).rbegin(), (a).rend()
#define SZ(a) ((int)(a).size())
#define PB(a) push_back(a)
#define EB(...) emplace_back(__VA_ARGS__)
#define MP(a, b) make_pair(a, b)
#define MT(...) make_tuple(__VA_ARGS__)
using namespace std;
using pii = pair<int, int>;
template <class T> inline bool chmin(T& a, T b) { if (a > b) { a = b; return 1; } return 0; }
template <class T> inline bool chmax(T& a, T b) { if (a < b) { a = b; return 1; } return 0; }
const int MOD = 1000000007;
const int INF = 1LL << 30;
const double EPS = 1e-10;

int x[3], y[3];
double d[3];

signed main() {
  cin.tie(0);
  ios_base::sync_with_stdio(false);
  cout << fixed << setprecision(12);
  
  REP(i, 3) cin >> x[i] >> y[i];
  double mx = 0, sum = 0;
  REP(i, 3) {
    d[i] = hypot(x[i]-x[(i+1)%3], y[i]-y[(i+1)%3]);
    chmax(mx, d[i]);
    sum += d[i];
  }
  x[1] -= x[0]; y[1] -= y[0];
  x[2] -= x[0]; y[2] -= y[0];
  double S = abs(x[1]*y[2]-x[2]*y[1])/2.0;
  double r = 2*S/sum;
  cout << r*mx/(2*r+mx) << endl;

  return 0;
}