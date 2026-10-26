#include <bits/stdc++.h>

using namespace std;

#define int long long
#define FOR(i, a, b) for(ll i = (a); i < (b); ++i)
#define FORR(i, a, b) for(ll i = (a); i > (b); --i)
#define REP(i, n) for(ll i = 0; i < (n); ++i)
#define REPR(i, n) for(ll i = n; i >= 0; i--)
#define FOREACH(x, a) for(auto &(x) : (a))
#define VECCIN(x)                                                              \
    for(auto &youso_ : (x)) cin >> youso_
#define bitcnt __builtin_popcount
#define SZ(x) ((ll)(x).size())
#define fi first
#define se second
#define All(a) (a).begin(), (a).end()
template <typename T = long long> inline T IN() {
    T x;
    cin >> x;
    return (x);
}
inline void CIN() {}
template <class Head, class... Tail>
inline void CIN(Head &&head, Tail &&... tail) {
    cin >> head;
    CIN(move(tail)...);
}
#define CINT(...)                                                              \
    int __VA_ARGS__;                                                           \
    CIN(__VA_ARGS__)
#define DCIN(...)                                                              \
    double __VA_ARGS__;                                                        \
    CIN(__VA_ARGS__)
#define LCIN(...)                                                              \
    ll __VA_ARGS__;                                                            \
    CIN(__VA_ARGS__)
#define SCIN(...)                                                              \
    string __VA_ARGS__;                                                        \
    CIN(__VA_ARGS__)
#define Yes(a) cout << (a ? "Yes" : "No") << "\n"
#define YES(a) cout << (a ? "YES" : "NO") << "\n"
#define Printv(v)                                                              \
    {                                                                          \
        FOREACH(x, v) { cout << x << " "; }                                    \
        cout << "\n";                                                          \
    }
template <typename T = string> inline void eputs(T s) {
    cout << s << "\n";
    exit(0);
}
template <typename A, size_t N, typename T>
void Fill(A (&array)[N], const T &val) {
    std::fill((T *)array, (T *)(array + N), val);
}
template <typename T> using PQG = priority_queue<T, vector<T>, greater<T>>;
template <typename T> using PQ = priority_queue<T>;

typedef long long ll;
typedef unsigned long long ul;
typedef vector<ll> VL;
typedef pair<ll, ll> PL;

const int INF = 1e9;
const int MOD = 1e9 + 7;
const double PI = atan(1.0) * 4.0;
// const int MOD = 998244353;
const ll LINF = 9e18;
const ll dx[] = {1, 0, -1, 0};
const ll dy[] = {0, 1, 0, -1};

void cinfast() {
    cin.tie(0);
    ios::sync_with_stdio(false);
}

const ll sup = 1e8;

vector<PL> F;

template <typename T = ll> vector<T> LIS(vector<T> v, T ini) {
    ll n = v.size();
    vector<T> ret(n, ini);
    REP(i, n) { *lower_bound(All(ret), v[i]) = v[i]; }
    ret.erase(lower_bound(All(ret), ini), ret.end());
    return ret;
}

signed main() {
    cinfast();
    LCIN(x1, y1, x2, y2, N);
    VL X(N), Y(N);
    REP(i, N) cin >> X[i] >> Y[i];
    if(x1 > x2) {
        x1 = sup - x1;
        x2 = sup - x2;
        REP(i, N) X[i] = sup - X[i];
    }
    if(y1 > y2) {
        y1 = sup - y1;
        y2 = sup - y2;
        REP(i, N) Y[i] = sup - Y[i];
    }
    REP(i, N) {
        if(y1 <= Y[i] && Y[i] <= y2 && x1 <= X[i] && X[i] <= x2)
            F.emplace_back(X[i], Y[i]);
    }
    sort(All(F));
    VL ys;
    REP(i, F.size()) ys.emplace_back(F[i].se);
    auto lis = LIS<ll>(ys, LINF);
    double ans = 100.0 * (x2 - x1 + y2 - y1);
    ans += (5.0 * PI - 20.0) * lis.size();
    if(lis.size() == min(x2 - x1, y2 - y1) + 1) ans += 5.0 * PI;
    printf("%.15f\n", ans);
}
