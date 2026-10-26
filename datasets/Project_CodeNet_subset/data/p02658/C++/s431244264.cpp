#include <bits/stdc++.h>
typedef long long ll;
#define _GLIBCXX_DEBUG
#define int long long
using namespace std;
#define REP(i, n) for (ll i = 0; i < (ll)(n); i++)
#define REPR(i, n) for (ll i = (ll)(n)-1; i >= 0; i--)
#define FOR(i, a, b) for (ll i = a; i <= (ll)(b); i++)
#define FORD(i, a, b) for (ll i = a; i >= (ll)(b); i--)
#define ALL(x) (x).begin(), (x).end()
#define SIZE(x) ((ll)(x).size())
#define MAX(x) *max_element(ALL(x))
#define MAXIN(x) max_element(ALL(x)) - x.begin();
#define MIN(x) *min_element(ALL(x))
#define MININ(x) min_element(ALL(x)) - x.begin();
#define INF 1000000000000
#define MOD 100000070

#define MAXR 100000
#define PI 3.14159265358979323846

using namespace std;

bool is_product_overflow(int a, int b) {
    int prod = a * b;

    return (prod / b != a);
}

bool over(int a, int b) {
    if (a < LLONG_MAX / b) {
        return false;
    } else {
        return true;
    }
}

signed main() {
    int n;
    cin >> n;
    vector<int> A(n);
    REP(i, n) {
        cin >> A[i];
        if (A[i] == 0) {
            cout << "0" << endl;
            return 0;
        }
    }
    int ans = 1;
    int MM = 1000000000000000000;

    REP(i, n) {
        if (over(ans, A[i])) {
            cout << "-1" << endl;
            return 0;
        }

        ans *= A[i];

        if (ans > MM) {
            cout << "-1" << endl;
            return 0;
        }
    }

    cout << ans << endl;
    cout << " " << endl;
    return 0;
}