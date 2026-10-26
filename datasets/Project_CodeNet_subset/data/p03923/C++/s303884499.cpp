#include <bits/stdc++.h>
using namespace std;

typedef vector<int> VI;
typedef vector<VI> VVI;
typedef vector<string> VS;
typedef pair<int, int> PII;
typedef long long LL;
typedef pair<LL, LL> PLL;

#define ALL(a)  (a).begin(),(a).end()
#define RALL(a) (a).rbegin(), (a).rend()
#define PB push_back
#define EB emplace_back
#define MP make_pair
#define SZ(a) int((a).size())
#define SORT(c) sort((c).begin(),(c).end())

#define FOR(i,a,b) for(int i=(a);i<(b);++i)
#define REP(i,n)  FOR(i,0,n)

#define FF first
#define SS second
template<class S, class T>
istream& operator>>(istream& is, pair<S,T>& p){
  return is >> p.FF >> p.SS;
}

const double EPS = 1e-10;
const double PI  = acos(-1.0);
const LL MOD = 1e9+7;
const LL INF = 1e18;

LL lpow(LL a, LL b){
  if(!b) return 1;
  return lpow(a*a, b/2) * (b&1?a:1);
}

LL dp[1000100];
int main(){
  cin.tie(0);
  ios_base::sync_with_stdio(false);

  LL N, A; cin >> N >> A;
  LL ans = 1e18;
  for(int t=1;t<40;++t){
	LL lb = 1, ub = pow(N, 1./t)+3;
	while(ub - lb > 1){
	  LL m = (lb + ub)/2;
	  LL p = lpow(m,t);
	  if(p >= N) ub = m;
	  else lb = m;
	}

	LL prod = 1;
	vector<LL> xs(t);
	REP(i,t)
	  xs[i] = ub, prod *= ub;
	REP(i,t){
	  if(prod / xs[i] * (xs[i] - 1) >= N){
		prod /= xs[i];
		xs[i]--;
		prod *= xs[i];
	  }
	}

	LL sum = 0;
	REP(i,t){
	  sum += xs[i];
	}
	assert(prod >= N);
	ans = min(ans, sum+A*(t-1));
  }

  cout << ans << endl;
  
  return 0;
}
