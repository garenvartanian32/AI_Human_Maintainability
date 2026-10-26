#include <iostream>
#include <cstdio>
#include <vector>
#include <algorithm>
#include <complex>
#include <cstring>
#include <cstdlib>
#include <string>
#include <cmath>
#include <queue>
using namespace std;

#define REP(i,n) for(int i=0;i<(int)n;++i)
#define FOR(i,c) for(__typeof((c).begin())i=(c).begin();i!=(c).end();++i)
#define ALL(c) (c).begin(), (c).end()
#define INF 1<<30

int main() {
  int n;
  while(cin>>n,n) {
    string f[n];
    int w[n],s[n];
    int x[n];
    REP(i,n) {
      cin >> f[i] >> w[i] >> s[i];
      x[i] = i;
    }
    int ans[n];
    double ansg = INF;
    do {
      int sum = 0;
      int ct;
      for (ct=n-2; ct>=0; --ct) {
        sum += w[x[ct+1]];
        if (s[x[ct]] < sum)
          break;
      }
      if (ct >= 0) continue;
      
      // REP(i,4) {
      //   cout << x[i] << " ";
      // }
      // cout << endl;
      double g = 0, hoge = 0;
      REP(i,n) {
        g += w[x[i]] * (i+1);
        hoge += w[x[i]];
      }
      g /= hoge;
      // cout << g << endl;
      if (ansg > g) {
        ansg = g;
        memcpy(ans,x,sizeof(x));
      }
    } while(next_permutation(x, x+n));
    REP(i,n)
      //     cout << ans[i] << " ";
     cout << f[ans[i]] << endl;
  }
}