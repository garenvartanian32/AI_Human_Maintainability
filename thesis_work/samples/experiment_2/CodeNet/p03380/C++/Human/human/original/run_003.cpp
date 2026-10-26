#include <bits/stdc++.h>

using namespace std;
using ll = long long;
using ld = long double;

#define REP(i,n) for(int i=0, i##_len=(n); i<i##_len; ++i)
#define ALL(x) (x).begin(),(x).end()

#define READ(x) (cin >> (x))
#define WRITE_N(x) (cout << (x) << endl)
#define WRITE(x) (cout << (x))
#define WRITE_YESNO(x) (WRITE_N(x ? "Yes" : "No"))

int main() {
  // get values from input
  cin.tie(0);
  ios::sync_with_stdio(false);

  int n;
  cin >> n;

  vector<int>a(n);
  for (auto i = 0; i < n; ++i) {
    cin >> a[i];
  }
 
  // main procedure
  sort(ALL(a));
  
  int a_i = *(a.end() - 1);
  int a_j;

  auto itr = lower_bound(ALL(a), a_i/2);
  int b = *itr;
  if (itr != a.begin()) {
    itr--;
  }
  int c = *itr;

  if (b*2 - a_i < a_i - c*2) {
    a_j = b;
  } else {
    a_j = c;
  }

  // output
  cout << a_i << " " << a_j << endl;

  return 0;
}
