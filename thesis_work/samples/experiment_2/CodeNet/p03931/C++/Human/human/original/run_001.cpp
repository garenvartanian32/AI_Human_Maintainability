#include <iostream>
#include <fstream>
#include <cstdio>
#include <cmath>
#include <vector>
#include <cstring>
#include <string>
#include <set>
#include <map>
#include <stack>
#include <queue>
#include <algorithm>
using namespace std;
 
#define REP(i,n) for(int i=0; i<n; ++i)
#define FOR(i,a,b) for(int i=a; i<=b; ++i)
#define FORR(i,a,b) for (int i=a; i>=b; --i)
#define pi M_PI
 
typedef long long ll;
typedef vector<int> VI;
typedef vector<ll> VL;
typedef vector<VI> VVI;
typedef pair<int,int> P;
typedef pair<ll,ll> PL;

#define MOD 1000000007

ll dp[101][256][101];

int main() {
    int n, k;
    cin >> n >> k;
    VI a(n);
    REP(i,n) cin >> a[i];

    dp[0][0][0] = 1;
    REP(i,n){
        REP(j,256){
            REP(k,n){
                dp[i+1][j^a[i]][k+1] = (dp[i+1][j^a[i]][k+1] + dp[i][j][k]) % MOD;
                dp[i+1][j][k] = (dp[i+1][j][k] + dp[i][j][k]) % MOD;
            }
        }
    }

    ll ans = 0;

    ll f = 1;
    FOR(p,1,n){
        f = (f * p) % MOD;
        ll r = (f * dp[n][k][p]) % MOD;
        ans = (ans + r) % MOD;
    }

    cout << ans << endl;

    return 0;
}