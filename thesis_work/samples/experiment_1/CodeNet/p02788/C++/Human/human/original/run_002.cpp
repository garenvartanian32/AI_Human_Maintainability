#include<iostream>
#include<iomanip>
#include<cstdio>
#include<vector>
#include<queue>
#include<set>
#include<map>
#include<string>
#include<algorithm>
#include<cmath>
//#include <bits/stdc++.h>

using namespace std;
typedef long long ll;
typedef vector<int> vint;
typedef vector<long long> vll;
template <class T> inline bool chmax(T& a, T b) {if (a < b) {a = b; return 1;} return 0;}
template <class T> inline bool chmin(T& a, T b) {if (a > b) {a = b; return 1;} return 0;}
template <class T> inline T gcd(T a, T b) {if(a==0) return b; return gcd(b%a,a);}
template <class T> inline T lcm(T a, T b) {return a * b / gcd(a,b);}
template <class T> inline T sum(vector<T> &vec) {T res=0; for (int i = 0; i < vec.size(); i++) res += vec[i]; return res;}
template <class T> inline T mpow(T a, T b) {if(b==0)return 1; T c = mpow(a,b/2);if(b%2==1)return c*c*a; return c*c;}
const int INF = 1 << 30;
const long long INFLL = 1LL << 60;
typedef pair<int, int> P;

#define REP(i,n) for (int (i)=0;(i)<(n);(i)++)
#define FOR(i,n) for (int (i)=0;(i)<(n);(i)++)
#define REPB(i,n) for (int (i)=((n)-1);(i)>=0;(i)--)
#define FORB(i,n) for (int (i)=((n)-1);(i)>=0;(i)--)
#define SORT(A) sort((A).begin(),(A).end())


ll bit[200001], N, D, A;
ll sum(ll i) {
    ll s = 0;
    while (i > 0) {
        s += bit[i];
        i -= i & -i;
    } return s;
}
void add(ll i, ll x) {
    while (i <= 200000) {
        bit[i] += x;
        i += i & -i;
    }
}

int main() {
    
    cin >> N >> D >> A;
    vll X(N), H(N);
    vector<pair<ll,ll>> G(N);
    FOR(i,N) {
        cin >> X[i] >> H[i];
        G[i] = make_pair(X[i],H[i]);
    }
    SORT(G);

    ll res = 0;

    ll idx = 0;
    vll idxs(N);
    FOR(i,N) {
        chmax(idx,(ll)i);
        while (idx+1 < N && G[idx+1].first <= G[i].first + 2*D) idx++;
        idxs[i] = idx;
        //cout << idx << " " << endl;
    }
    //FOR(i,N) cout << idxs[i] << " ";
    //cout << endl;

    FOR(i,N) {
        G[i].second -= sum(i+1);
        //cout << G[i].second << " ";
        if (G[i].second > 0) {
            ll d = (G[i].second+A-1)/A;
            add(i+1,A*d);
            add(idxs[i]+2,-A*d);
            res += d;
        }
    }
    cout << res << endl;

}
