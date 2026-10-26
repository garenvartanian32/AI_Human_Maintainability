#include <bits/stdc++.h>
using namespace std;

#define TRACE(x) cerr << #x << " :: " << x << endl
#define _ << " " <<
#define SZ(x) (int)(x).size()
#define ALL(x) (x).begin(),(x).end()
#define FOR(i,a,b) for(int i=(a);i<=(b);++i)
#define RFOR(i,a,b) for (int i=(a);i>=(b);--i)

const int mod = 998244353;
const int mxK = 305;

string S;
int K, N;
vector<int> C;
int dp[2][mxK][mxK], px[2][mxK][mxK], py[2][mxK][mxK];

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);
    cin >> S >> K;
    C.push_back(-1);
    int cur = 0;
    for(auto& x : S){
        if (x == '0') C.push_back(cur), cur = 0;
        else ++cur;
    }
    if (cur) C.push_back(cur);
    
    N = SZ(C)-1;
    K = min(K,SZ(S));
    FOR(x,0,K) FOR(y,0,K) {
        dp[0][x][y] = (x == y && x <= K);
        px[0][x][y] = (x > 0 ? px[0][x-1][y] : 0) + dp[0][x][y];
        px[0][x][y] %= mod;
        py[0][x][y] = (y > 0 ? py[0][x][y-1] : 0) + dp[0][x][y];
        py[0][x][y] %= mod;
    }
    FOR(i,1,N){
        int c = i&1, p = !c;
        FOR(x,0,K){
            FOR(y,0,x) {
                dp[c][x][y] = dp[p][x][y];

                dp[c][x][y] += (px[p][x+min(C[i],K-x)][y] - px[p][x][y] + mod) % mod;
                dp[c][x][y] %= mod;
                dp[c][x][y] += (py[p][x][x] - py[p][x][y] + mod) % mod;
                dp[c][x][y] %= mod;

                px[c][x][y] = (x > 0 ? px[c][x-1][y] : 0) + dp[c][x][y];
                px[c][x][y] %= mod;
                py[c][x][y] = (y > 0 ? py[c][x][y-1] : 0) + dp[c][x][y];
                py[c][x][y] %= mod;
            }
        }
    }
    cout << dp[N&1][0][0] << '\n';
}

