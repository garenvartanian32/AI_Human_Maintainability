#include <algorithm>
#include <bitset>
#include <cmath>
#include <cstring>
#include <deque>
#include <iomanip>
#include <iostream>
#include <map>
#include <queue>
#include <random>
#include <set>
#include <stack>
#include <string>
#include <vector>

#define mod 1000000007
#define INF2 9999999999
#define INF (1 << 30)
#define rep(i, n) for(int(i) = 0; (i) < (n); (i)++)

using namespace std;
using ll = __int64_t;

int dx[] = {1, 0, -1, 0};
int dy[] = {0, 1, 0, -1};
int DX[] = {1, 1, 0, -1, -1, -1, 0, 1};
int DY[] = {0, -1, -1, -1, 0, 1, 1, 1};

//約数列挙(1を含む)
vector<ll> divisor(ll n){
    vector<ll> res;
    for(ll i = 1; i*i <= n; i++){
        if(n % i == 0){
            res.push_back(i);
            if(i*i != n) res.push_back(n/i);
        }
    }
    return res;
}

void solve(){
    ll n, res=0;
    cin >> n;

    vector<ll> v = divisor(n);
    for(ll i = 0; i < v.size(); i++){
        ll t = v[i]-1;
        if(t != 0 && n/t == n%t){
            res += t;
        }
    }
    cout << res << endl;
}

int main() {
    solve();
    return 0;
}