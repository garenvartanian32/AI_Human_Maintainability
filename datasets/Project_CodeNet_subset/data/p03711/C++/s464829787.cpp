#include <bits/stdc++.h>

#define NREP() for(ll i = 0; i < n; i++)
#define MREP() for(ll j = 0; j < m; j++)
#define REP(i, n) for(ll i = 0; i < n; i++)
#define ALL(x) (x).begin(), (x).end()
#define MSG(x) cout << x << endl;
#define IPT(t, x) t ipt; cin >> ipt; x.push_back(ipt);
#define YN(x) x ? cout << "YES" << endl : cout << "NO" << endl;
#define Yn(x) x ? cout << "Yes" << endl : cout << "No" << endl;
#define yn(x) x ? cout << "yes" << endl : cout << "no" << endl;

using namespace std;
typedef long long ll;
typedef vector<ll> vl;
typedef vector<vector<ll>> vvl;
typedef vector<string> vs;
typedef vector<vector<string>> vvs;

const static ll MOD = 1e9 + 7;
ll n, cnt, ans, tmp, sum, a, b, c, d, k, m, h, w, q, x, y;
string s, t;

int main() {
    cin >> x >> y;
    if (x == 2) {
        Yn(y == 2)
        exit(0);
    }

    set<ll> g1;
    set<ll> g2;

    g1.insert(1);
    g1.insert(3);
    g1.insert(5);
    g1.insert(7);
    g1.insert(8);
    g1.insert(10);
    g1.insert(12);

    g2.insert(4);
    g2.insert(6);
    g2.insert(9);
    g2.insert(11);

    if (g1.count(x) != 0) {
        Yn(g1.count(y) != 0)
    } else {
        Yn(g2.count(y) != 0)
    }

}
