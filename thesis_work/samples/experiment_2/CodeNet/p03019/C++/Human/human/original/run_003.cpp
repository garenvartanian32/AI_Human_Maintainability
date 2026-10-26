#include <bits/stdc++.h>
using namespace std;

#define INF_LL (int64)1e18
#define INF (int32)1e9
#define REP(i, n) for(int64 i = 0;i < (n);i++)
#define FOR(i, a, b) for(int64 i = (a);i < (b);i++)
#define all(x) x.begin(),x.end()
#define fs first
#define sc second

using int32 = int_fast32_t;
using uint32 = uint_fast32_t;
using int64 = int_fast64_t;
using uint64 = uint_fast64_t;
using PII = pair<int32, int32>;
using PLL = pair<int64, int64>;

const double eps = 1e-10;

template<typename A, typename B>inline void chmin(A &a, B b){if(a > b) a = b;}
template<typename A, typename B>inline void chmax(A &a, B b){if(a < b) a = b;}

template<typename T>
vector<T> make_v(size_t a){return vector<T>(a);}

template<typename T,typename... Ts>
auto make_v(size_t a,Ts... ts){
  return vector<decltype(make_v<T>(ts...))>(a,make_v<T>(ts...));
}

template<typename T,typename U,typename... V>
typename enable_if<is_same<T, U>::value!=0>::type
fill_v(U &u,const V... v){u=U(v...);}

template<typename T,typename U,typename... V>
typename enable_if<is_same<T, U>::value==0>::type
fill_v(U &u,const V... v){
  for(auto &e:u) fill_v<T>(e,v...);
}


int main(void) {
  int64 N, X;
  using T = tuple<int64, int64, int64>;
  cin >> N >> X;
  vector<T> test(N);
  int64 sa = 0;
  REP(i, N) {
    int64 b, l, u;
    cin >> b >> l >> u;
    sa += b*l;
    test[i] = T(b, l, u);
  }

  int64 l = -1, r = (int64)1e11, m;
  vector<PLL> mx(N);
  REP(i, N) {
    int64 b, l, u;
    tie(b, l, u) = test[i];
    mx[i] = PLL(u*(X-b)+l*b, i);
  }
  sort(all(mx), greater<PLL>());

  auto ok = [&](int64 x) {
    vector<int64> used(N, 0);
    int64 sum = 0, res = 0;
    REP(i, min(x/X, N)) {
      sum += mx[i].fs;
      used[mx[i].sc] = 1;
    }
    res = sum;
    REP(i, N) {
      int64 b, l, u;
      tie(b, l, u) = test[i];
      int64 ei = 0;
      if (b < x%X) ei = u*(x%X-b) + l*b;
      else ei = l*(x%X);
      if (used[i]) {
        ei -= u*(X-b)+l*b;
        if (x/X < N)
          ei += mx[x/X].fs;
      }
      res = max(res, sum+ei);
    }
    return res >= sa;
  };

  while (r - l > 1) {
    m = (l + r) >> 1;
    if (ok(m)) {
      r = m;
    } else {
      l = m;
    }
  }
  cout << r << endl;
}