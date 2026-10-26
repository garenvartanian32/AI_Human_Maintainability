#include "bits/stdc++.h"
#define   LF      '\n'
#define   ALL(x)  x.begin(), x.end()
#define   iostreamBooster()  do{cin.tie(nullptr);ios::sync_with_stdio(false);}while(0)
using namespace std;
typedef int64_t i64;
typedef pair<int,int> pii;
template<class A, class B>inline bool chmax(A &a, const B &b){return b>a ? a=b,1 : 0;}
template<class A, class B>inline bool chmin(A &a, const B &b){return b<a ? a=b,1 : 0;}
constexpr int INF = 0x3f3f3f3f;
constexpr i64 LINF = 0x3f3f3f3f'3f3f3f3fLL;

i64 a[1000100];

i64 f(i64 n) {
  if (n & 1) {
    return 3*n + 1;
  } else {
    return n/2;
  }
}

signed main()
{
  set<i64> U;

  int ans = 0;
  cin >> a[1];
  U.insert(a[1]);

  for (int i = 2; i <= 1000005; ++i) {
    a[i] = f(a[i-1]);

    if (U.count(a[i])) {
      ans = i;
      break;
    } else {
      U.insert(a[i]);
    }
  }

  cout << ans << endl;

  return 0;
}

