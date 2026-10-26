//include
//------------------------------------------
#include <vector>
#include <list>
#include <map>
#include <set>
#include <deque>
#include <stack>
#include <bitset>
#include <algorithm>
#include <functional>
#include <numeric>
#include <utility>
#include <sstream>
#include <iostream>
#include <iomanip>
#include <cstdio>
#include <cmath>
#include <cstdlib>
#include <cctype>
#include <string>
#include <cstring>
#include <ctime>
#include <climits>
#include <queue>

using namespace std;

//typedef
//------------------------------------------
typedef vector<int> VI;
typedef vector<VI> VVI;
typedef vector<string> VS;
typedef pair<int, int> PII;
typedef long long LL;

//container util
//------------------------------------------
#define ALL(a)  (a).begin(),(a).end()
#define RALL(a) (a).rbegin(), (a).rend()
#define PB push_back
#define MP make_pair
#define SZ(a) int((a).size())
#define EACH(i,c) for(typeof((c).begin()) i=(c).begin(); i!=(c).end(); ++i)
#define EXIST(s,e) ((s).find(e)!=(s).end())
#define SORT(c) sort((c).begin(),(c).end())

//repetition
//------------------------------------------
#define FOR(i,a,b) for(int i=(a);i<(b);++i)
#define REP(i,n)  FOR(i,0,n)

//constant
//--------------------------------------------
const double EPS = 1e-10;
const double PI  = acos(-1.0);

int main(){
  cin.tie(0);
  ios_base::sync_with_stdio(false);

  int N; cin >> N;
  double dist[2] = {-1, -1};
  int time[2] = {-1,-1};
  int f_, a_, t_, x_, y_;
  cin >> f_ >> a_ >> t_ >> x_ >> y_;
  REP(i,N-1){
	int f, a, t, x, y;
	cin >> f >> a >> t >> x >> y;
	if(t_ == t && a_ != a){
	  double dist_ = sqrt((x - x_)*(x-x_)+(y-y_)*(y-y_));
	  int time_ = f - f_;
	  if(dist_ > dist[t] || dist[t] == dist_ && time_ < time[t]){
		dist[t] = dist_, time[t] = time_;
	  }
	}
	f_ = f, a_ = a, t_ = t, x_ = x, y_ = y;
  }

  REP(i,2)
	if(time[i] < 0)
	  cout << -1 << " " << -1 << endl;
	else
	  cout << fixed << setprecision(9) << dist[i] << " " << (time[i]*1./60) << endl;
  
  return 0;
}