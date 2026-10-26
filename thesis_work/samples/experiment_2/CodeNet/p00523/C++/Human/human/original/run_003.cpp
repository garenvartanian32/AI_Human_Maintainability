#include <bits/stdc++.h>
using namespace std;

using VI = vector<int>;
using VVI = vector<VI>;
using PII = pair<int, int>;
using LL = long long;
using VL = vector<LL>;
using VVL = vector<VL>;
using PLL = pair<LL, LL>;
using VS = vector<string>;

#define ALL(a)  begin((a)),end((a))
#define RALL(a) (a).rbegin(), (a).rend()
#define PB push_back
#define EB emplace_back
#define MP make_pair
#define SZ(a) int((a).size())
#define SORT(c) sort(ALL((c)))
#define RSORT(c) sort(RALL((c)))
#define UNIQ(c) (c).erase(unique(ALL((c))), end((c)))

#define FOR(i,a,b) for(int i=(a);i<(b);++i)
#define REP(i,n)  FOR(i,0,n)

#define FF first
#define SS second
template<class S, class T>
istream& operator>>(istream& is, pair<S,T>& p){
  return is >> p.FF >> p.SS;
}
template<class S, class T>
ostream& operator<<(ostream& os, const pair<S,T>& p){
  return os << p.FF << " " << p.SS;
}
template<class T>
void maxi(T& x, T y){
  if(x < y) x = y;
}
template<class T>
void mini(T& x, T y){
  if(x > y) x = y;
}


const double EPS = 1e-10;
const double PI  = acos(-1.0);
const LL MOD = 1e9+7;

int main(){
  cin.tie(0);
  ios_base::sync_with_stdio(false);

  int N;
  cin >> N;
  VL xs(N*2);
  REP(i,N){
	cin >> xs[i];
	xs[i+N] = xs[i];
  }
  VL acc(2*N+1);
  REP(i,N*2) acc[i+1] = acc[i] + xs[i];

  LL lb = 0, ub = 1e18;
  while(ub-lb > 1){
	LL m = (lb + ub) / 2;
	bool ok = false;
	REP(i,N){
	  int b1 = lower_bound(begin(acc)+i, end(acc), acc[i]+m) - begin(acc);
	  if(b1 == 2*N+1) continue;
	  int b2 = lower_bound(begin(acc)+b1, end(acc), acc[b1]+m) - begin(acc);
	  if(b2 == 2*N+1) continue;
	  int b3 = lower_bound(begin(acc)+b2, end(acc), acc[b2]+m) - begin(acc);
	  if(b3 == 2*N+1) continue;
	  if(b3-i <= N){
		ok = true;
		break;
	  }
	}
	if(ok) lb = m;
	else ub = m;
  }
  cout << lb << endl;

  return 0;
}