#include <bits/stdc++.h>
#define FOR(i,k,n) for(int i=(k);i<(int)(n);++i)
#define REP(i,n) FOR(i,0,n)
#define ALL(x) begin(x),end(x)

using namespace std;
using ld = long double;
constexpr ld eps = 1e-4;

ld calc(const vector<pair<ld,ld>> &vv, const vector<ld> &coeff, const int i, const ld x) {
  ld sum = 0;
  REP(j,i) {
    ld mul = 1;
    REP(k,j) {
      mul *= x - vv[k].first;
    }
    sum += mul * coeff[j];
  }
  return sum;
}

ld mul(const vector<pair<ld,ld>> &vv, const int i, const ld x) {
  ld mul = 1;
  REP(j,i) {
    mul *= x - vv[j].first;
  }
  return mul;
}

ld val(const vector<pair<ld,ld>> &vv, const ld x) {
  int n = vv.size();
  vector<ld> coeff;
  coeff.push_back(vv[0].second);
  FOR(i,1,n) {
    ld sum = calc(vv, coeff, i, vv[i].first);
    coeff.push_back((vv[i].second - sum) / mul(vv, i, vv[i].first));
  }
  return calc(vv, coeff, n, x);
}

int main() {
  while (true) {
    int d;
    cin>>d;
    if (!d) break;
    vector<pair<ld,ld>> vv;
    REP(i,d+3) {
      ld v;
      cin>>v;
      vv.emplace_back((ld)i, v);
    }
    set<int> s;
    REP(i,d+3) s.insert(i);
    REP(i,d+3)REP(j,i) {
      vector<pair<ld,ld>> tmp;
      REP(k,d+3) {
        if (k==i || k==j) continue;
        tmp.push_back(vv[k]);
      }
      ld diffi = val(tmp, vv[i].first) - vv[i].second;
      ld diffj = val(tmp, vv[j].first) - vv[j].second;
      if (abs(diffi) < eps || abs(diffj) < eps) {
        REP(k,d+3) {
          if (k==i || k==j) continue;
          s.erase(k);
        }
      }
    }
    assert(s.size() == 1);
    cout << *begin(s) << endl;
  }
  return 0;
}