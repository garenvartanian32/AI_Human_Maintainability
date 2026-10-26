#include <bits/stdc++.h>
using namespace std;

#define int long long
#define pb push_back 
#define mp make_pair
#define fr first
#define sc second
#define Rep(i, n) for( int i = 0; i < (n); i++ )
#define Rrep(i, a, n) for( int i = (a); i < (n); i++ )
#define All(v) v.begin(), v.end()

typedef pair<int, int> Pii; 
typedef pair<int, Pii> Pip;
const int INF = 1107110711071107;

signed main() {
  int a, b, c;
  cin >> a >> b >> c;
  if ( c > a+b ) cout << "NA" << endl;
  else {
    cout << max(0LL, c-a) << endl;
  }
  return 0;
}