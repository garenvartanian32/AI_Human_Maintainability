#include <iostream>
#include <algorithm>
#include <numeric>
#include <vector>
#include <cassert>
#include <string>
#include <memory.h>
#include <queue>
#include <cstdio>
#include <cstdlib>
#include <set>
#include <map>
#include <cctype>
#include <iomanip>
#include <sstream>
#include <cctype>
#include <fstream>
#include <complex>
#include <cmath>
using namespace std;

#define REP2(i, m, n) for(int i = (int)(m); i < (int)(n); i++)
#define REP(i, n) REP2(i, 0, n)
#define ALL(c) (c).begin(), (c).end()
#define ITER(c) __typeof((c).begin())
#define PB(e) push_back(e)
#define FOREACH(i, c) for(ITER(c) i = (c).begin(); i != (c).end(); ++i)
#define MP(a, b) make_pair(a, b)
#define PARITY(n) ((n) & 1)

typedef long long ll;
typedef pair<ll, ll> P;
typedef complex<double> xy_t;
const int INF = 1000 * 1000 * 1000 + 7;
const double EPS = 1e-10;

bool ok(const vector<int> &xs, const vector<double> &vs){
  
  // lagrange interpolation
  double fv = 0;
  
  REP(i, xs.size() - 1){
    double t = 1;
    REP(j, xs.size() - 1)if(i != j){
      t *= (double)(xs.back() - xs[j]) / (xs[i] - xs[j]);
    }
    fv += vs[i] * t;
  }

  return abs(fv - vs.back()) < 1e-1;
}

int main(){
  int d;
  while(cin >> d && d){
    vector<double> val(d + 3);
    REP(i, d + 3) cin >> val[i];
    REP(i, d + 3){
      vector<double> vs;
      vector<int> xs;
      
      REP(j, d + 3) if(i != j){
	xs.push_back(j);
	vs.push_back(val[j]);
      }
      
      if(ok(xs, vs)){
	cout << i << endl;
	break;
      }
    }
  }
  return 0;
}