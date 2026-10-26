#include "bits/stdc++.h"
using namespace std;

//#define int long long
#define DBG 1
#define dump(o) if(DBG){cerr<<#o<<" "<<o<<endl;}
#define dumpc(o) if(DBG){cerr<<#o; for(auto &e:(o))cerr<<" "<<e;cerr<<endl;}
#define rep(i,a,b) for(int i=(a);i<(b);i++)
#define rrep(i,a,b) for(int i=(b)-1;i>=(a);i--)
#define each(it,c) for(auto it=(c).begin();it!=(c).end();it++)
#define all(c) c.begin(),c.end()
const int INF = sizeof(int) == sizeof(long long) ? 0x3f3f3f3f3f3f3f3fLL : 0x3f3f3f3f;
const int MOD = (int)(1e9 + 7);

signed main() {
	vector<int> W(10); rep(i, 0, 10) { cin >> W[i]; }
	vector<int> K(10); rep(i, 0, 10) { cin >> K[i]; }
	sort(W.begin(), W.end(), greater<int>());
	sort(K.begin(), K.end(), greater<int>());
	int w = accumulate(W.begin(), W.begin() + 3, 0);
	int k = accumulate(K.begin(), K.begin() + 3, 0);
	cout << w << " " << k << endl;
	return 0;
}