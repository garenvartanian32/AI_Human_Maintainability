#include <bits/stdc++.h>
using namespace std;
//T+N
//#pragma GCC optimize ("O3")
//#pragma GCC target ("sse4")
#define endl "\n"
typedef long long ll;
typedef long double ld;
typedef unsigned long long ull;
template<class T, class T2> inline bool chkmax(T &x, const T2 &y) { return x < y ? x = y, 1 : 0; }
template<class T, class T2> inline bool chkmin(T &x, const T2 &y) { return x > y ? x = y, 1 : 0; }
const ll mod = 1e9 + 7;
template<class T> inline void fix(T &x) {if(x >= mod | x <= -mod) {x %= mod;} if(x < 0) {x += mod;}}
#define out(x) cout << __LINE__ << ": " << (#x) << " = " << (x) << endl
 
signed main() {
    //ios_base::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
    ull a, b, c, d, t;
    cin >> a >> b >> c >> d >> t;
    ull dist = labs(a - c);
    ull vel = b - d;
    if(d >= b) {
        cout << "NO" << endl;
    } else if(vel * t >= dist) {
        cout << "YES" << endl;
    } else {
        cout << "NO" << endl;
    }
    return 0;
}
 