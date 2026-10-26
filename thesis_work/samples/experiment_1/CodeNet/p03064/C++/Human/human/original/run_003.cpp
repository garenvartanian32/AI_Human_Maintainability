//todo 文字数を少なくする
//mintより高速に計算したい時用に modmul() modadd (正の値限定) を作る
//#pragma GCC optimize ("-O3")
#include <bits/stdc++.h>
using namespace std;
//@起動時
struct initon {
    initon() {
        cin.tie(0);
        ios::sync_with_stdio(false);
        cout.setf(ios::fixed);
        cout.precision(16);
        srand((unsigned) clock() + (unsigned) time(NULL));
    };
} __initon;
//衝突対策
#define ws ___ws

//@必須構造
struct T {
    int f, s, t;
    T() { f = -1, s = -1, t = -1; }
    T(int f, int s, int t) : f(f), s(s), t(t) {}
    bool operator<(const T &r) const {
        return f != r.f ? f < r.f : s != r.s ? s < r.s : t < r.t;
        //return f != r.f ? f > r.f : s != r.s ? s > r.s : t > r.t; 大きい順
    }
    bool operator>(const T &r) const {
        return f != r.f ? f > r.f : s != r.s ? s > r.s : t > r.t;
        //return f != r.f ? f > r.f : s != r.s ? s > r.s : t > r.t; 小さい順
    }
    bool operator==(const T &r) const {
        return f == r.f && s == r.s && t == r.t;
    }
    bool operator!=(const T &r) const {
        return f != r.f || s != r.s || t != r.t;
    }
    int operator[](int i) {
        assert(i < 3);
        return i == 0 ? f : i == 1 ? s : t;
    }
};
struct F {
    int a, b, c, d;
    F() { a = -1, b = -1, c = -1, d = -1; }
    F(int a, int b, int c, int d) : a(a), b(b), c(c), d(d) {}
    bool operator<(const F &r) const {
        return a != r.a ? a < r.a : b != r.b ? b < r.b : c != r.c ? c < r.c : d < r.d;
//        return a != r.a ? a > r.a : b != r.b ? b > r.b : c != r.c ? c > r.c : d > r.d;
    }
    bool operator>(const F &r) const {
        return a != r.a ? a > r.a : b != r.b ? b > r.b : c != r.c ? c > r.c : d > r.d;
//        return a != r.a ? a < r.a : b != r.b ? b < r.b : c != r.c ? c < r.c : d < r.d;
    }
    bool operator==(const F &r) const {
        return a == r.a && b == r.b && c == r.c && d == r.d;
    }
    bool operator!=(const F &r) const {
        return a != r.a || b != r.b || c != r.c || d != r.d;
    }
    int operator[](int i) {
        assert(i < 4);
        return i == 0 ? a : i == 1 ? b : i == 2 ? c : d;
    }
};

T mt(int a, int b, int c) {
    return T(a, b, c);
}
//@マクロ省略系 型,構造

#define int long long
#define ll long long
#define double long double
#define ull unsigned long long
using dou = double;
using itn = int;
using str = string;
using bo= bool;
#define au auto
using P = pair<ll, ll>;
using pd =pair<dou, dou>;
#define fi first
#define se second
#define vec vector
#define beg begin
#define rbeg rbegin
#define con continue
#define bre break
#define brk break
#define is ==

#define maxq 1
#define minq -1

#define ZERO(a) memset(a,0,sizeof(a))
#define MINUS(a) memset(a,0xff,sizeof(a))
#define MALLOC(type, len) (type*)malloc((len) * sizeof(type))

//マクロ省略系 コンテナ

using vi = vector<int>;
#define _overloadvvi(_1, _2, _3, _4, name, ...) name
#define vvi0() vec<vi>
#define vvi1(a) vec<vi> a
#define vvi2(a, b) vec<vi> a(b)
#define vvi3(a, b, c) vec<vi> a(b,vi(c))
#define vvi4(a, b, c, d) vec<vi> a(b,vi(c,d))
#define vvi(...) _overloadvvi(__VA_ARGS__,vvi4,vvi3,vvi2 ,vvi1,vvi0)(__VA_ARGS__)


using vl = vector<ll>;
#define _overloadvvl(_1, _2, _3, _4, name, ...) name
#define vvl1(a) vec<vl> a
#define vvl2(a, b) vec<vl> a(b)
#define vvl3(a, b, c) vec<vl> a(b,vl(c))
#define vvl4(a, b, c, d) vec<vl> a(b,vl(c,d))
#define vvl(...) _overloadvvl(__VA_ARGS__,vvl4,vvl3,vvl2 ,vvl1)(__VA_ARGS__)

using vb = vector<bool>;
#define _overloadvvb(_1, _2, _3, _4, name, ...) name
#define vvb1(a) vec<vb> a
#define vvb2(a, b) vec<vb> a(b)
#define vvb3(a, b, c) vec<vb> a(b,vb(c))
#define vvb4(a, b, c, d) vec<vb> a(b,vb(c,d))
#define vvb(...) _overloadvvb(__VA_ARGS__,vvb4,vvb3,vvb2 ,vvb1)(__VA_ARGS__)

using vs = vector<string>;
#define _overloadvvs(_1, _2, _3, _4, name, ...) name
#define vvs1(a) vec<vs> a
#define vvs2(a, b) vec<vs> a(b)
#define vvs3(a, b, c) vec<vs> a(b,vs(c))
#define vvs4(a, b, c, d) vec<vs> a(b,vs(c,d))
#define vvs(...) _overloadvvs(__VA_ARGS__,vvs4,vvs3,vvs2 ,vvs1)(__VA_ARGS__)

using vd = vector<double>;
#define _overloadvvd(_1, _2, _3, _4, name, ...) name
#define vvd1(a) vec<vd> a
#define vvd2(a, b) vec<vd> a(b)
#define vvd3(a, b, c) vec<vd> a(b,vd(c))
#define vvd4(a, b, c, d) vec<vd> a(b,vd(c,d))
#define vvd(...) _overloadvvd(__VA_ARGS__,vvd4,vvd3,vvd2 ,vvd1)(__VA_ARGS__)

using vc=vector<char>;
#define _overloadvvc(_1, _2, _3, _4, name, ...) name
#define vvc1(a) vec<vc> a
#define vvc2(a, b) vec<vc> a(b)
#define vvc3(a, b, c) vec<vc> a(b,vc(c))
#define vvc4(a, b, c, d) vec<vc> a(b,vc(c,d))
#define vvc(...) _overloadvvc(__VA_ARGS__,vvc4,vvc3,vvc2 ,vvc1)(__VA_ARGS__)

using vp = vector<P>;
#define _overloadvvp(_1, _2, _3, _4, name, ...) name
#define vvp1(a) vec<vp> a
#define vvp2(a, b) vec<vp> a(b)
#define vvp3(a, b, c) vec<vp> a(b,vp(c))
#define vvp4(a, b, c, d) vec<vp> a(b,vp(c,d))
#define vvp(...) _overloadvvp(__VA_ARGS__,vvp4,vvp3,vvp2 ,vvp1)(__VA_ARGS__)

using vt = vector<T>;
#define _overloadvvt(_1, _2, _3, _4, name, ...) name
#define vvt1(a) vec<vt> a
#define vvt2(a, b) vec<vt> a(b)
#define vvt3(a, b, c) vec<vt> a(b,vt(c))
#define vvt4(a, b, c, d) vec<vt> a(b,vt(c,d))
#define vvt(...) _overloadvvt(__VA_ARGS__,vvt4,vvt3,vvt2 ,vvt1)(__VA_ARGS__)

#define v3i(a, b, c, d) vector<vector<vi>> a(b, vector<vi>(c, vi(d)))
#define v3d(a, b, c, d) vector<vector<vd>> a(b, vector<vd>(c, vd(d)))
#define v3m(a, b, c, d) vector<vector<vm>> a(b, vector<vm>(c, vm(d)))

#define _vvi vector<vi>
#define _vvl vector<vl>
#define _vvb vector<vb>
#define _vvs vector<vs>
#define _vvd vector<vd>
#define _vvc vector<vc>
#define _vvp vector<vp>

template<typename T> vector<T> make_v(size_t a) { return vector<T>(a); }
template<typename T, typename... Ts> auto make_v(size_t a, Ts... ts) {
    return vector<decltype(make_v<T>(ts...))>(a, make_v<T>(ts...));
}
#define vni(name, ...) auto name = make_v<int>(__VA_ARGS__)
#define vnb(name, ...) auto name = make_v<bool>(__VA_ARGS__)
#define vns(name, ...) auto name = make_v<string>(__VA_ARGS__)
#define vnd(name, ...) auto name = make_v<double>(__VA_ARGS__)
#define vnc(name, ...) auto name = make_v<char>(__VA_ARGS__)
#define vnp(name, ...) auto name = make_v<P>(__VA_ARGS__)

#define PQ priority_queue<ll, vector<ll>, greater<ll> >
#define tos to_string
using mapi = map<int, int>;
using mapp = map<P, int>;
using mapd = map<dou, int>;
using mapc = map<char, int>;
using maps = map<str, int>;
using seti = set<int>;
using setd = set<dou>;
using setc = set<char>;
using sets = set<str>;
using qui = queue<int>;
#define bset bitset
#define uset unordered_set
#define mset multiset
#define umap unordered_map
#define umapi unordered_map<int,int>
#define umapp unordered_map<P,int>
#define mmap multimap

//マクロ 繰り返し
#define _overloadrep(_1, _2, _3, _4, name, ...) name
# define _rep(i, n) for(int i = 0,_lim=n; i < _lim ; i++)
#define repi(i, m, n) for(int i = m,_lim=n; i < _lim ; i++)
#define repadd(i, m, n, ad) for(int i = m,_lim=n; i < _lim ; i+= ad)
#define rep(...) _overloadrep(__VA_ARGS__,repadd,repi,_rep,)(__VA_ARGS__)
#define _rer(i, n) for(int i = n; i >= 0 ; i--)
#define reri(i, m, n) for(int i = m,_lim=n; i >= _lim ; i--)
#define rerdec(i, m, n, dec) for(int i = m,_lim=n; i >= _lim ; i-=dec)
#define rer(...) _overloadrep(__VA_ARGS__,rerdec,reri,_rer,)(__VA_ARGS__)
#define fora(a, b) for(auto&& a : b)
#define forg(gi, ve) for (int gi = 0, f, t, c; gi < ve.size() && (f = ve[gi].f, t = ve[gi].t, c = ve[gi].c, true); gi++)
#define fort(gi, ve) for (int gi = 0, f, t, c; gi < ve.size() && (f = ve[gi].f, t = ve[gi].t, c = ve[gi].c, true); gi++)if(t!=p)
//#define fort(gi, ve) for (int gi = 0, f, t, c;gi<ve.size()&& (gi+= (ve[gi].t==p))< ve.size() && (f = ve[gi].f,t=ve[gi].t, c = ve[gi].c,true); gi++)

//マクロ 定数
#define k3 1010
#define k4 10101
#define k5 101010
#define k6 1010101
#define k7 10101010
const int inf = (int) 1e9 + 100;
const ll linf = (ll) 1e18 + 100;
const char infc = '{';
const string infs = "{";
const double eps = 1e-9;
const double PI = 3.1415926535897932384626433832795029L;
ll ma = numeric_limits<ll>::min();
ll mi = numeric_limits<ll>::max();
const int y4[] = {-1, 1, 0, 0};
const int x4[] = {0, 0, -1, 1};
const int y8[] = {0, 1, 0, -1, -1, 1, 1, -1};
const int x8[] = {1, 0, -1, 0, 1, -1, 1, -1};

//マクロ省略形 関数等
#define arsz(a) (sizeof(a)/sizeof(a[0]))
#define sz(a) ((int)(a).size())
//#define rs resize
#define mp make_pair
#define pb push_back
#define pf push_front
#define eb emplace_back
#define all(a) (a).begin(),(a).end()
#define rall(a) (a).rbegin(),(a).rend()

constexpr bool ev(int a) { return !(a & 1); }
constexpr bool od(int a) { return (a & 1); }
//@拡張系 こう出来るべきというもの

//埋め込み 存在を意識せずに機能を増やされているもの
namespace std {
    template<> class hash<std::pair<signed, signed>> {
    public:
        size_t operator()(const std::pair<signed, signed> &x) const {
            return hash<ll>()(((ll) x.first << 32) + x.second);
        }
    };
    template<> class hash<std::pair<ll, ll>> {
    public:
        //大きいllが渡されると、<<32でオーバーフローするがとりあえず問題ないと判断
        size_t operator()(const std::pair<ll, ll> &x) const {
            return hash<ll>()(((ll) x.first << 32) + x.second);
        }
    };
}
istream &operator>>(istream &iss, P &a) {
    iss >> a.first >> a.second;
    return iss;
}
template<typename T> istream &operator>>(istream &iss, vector<T> &vec) {
    for (T &x: vec) iss >> x;
    return iss;
}
template<class T, class U> ostream &operator<<(ostream &os, pair<T, U> p) {
    os << p.fi << " " << p.se << endl;
    return os;
}
ostream &operator<<(ostream &os, T p) {
    os << p.f << " " << p.s << " " << p.t;
    return os;
}
ostream &operator<<(ostream &os, F p) {
    os << p.a << " " << p.b << " " << p.c << " " << p.d;
    return os;
}
template<typename T> ostream &operator<<(ostream &os, vector <T> &vec) {
    for (int i = 0; i < vec.size(); i++)os << vec[i] << (i + 1 == vec.size() ? "" : " ");
    return os;
}
template<typename T> ostream &operator<<(ostream &os, vector <vector<T>> &vec) {
    for (int i = 0; i < vec.size(); i++) {
        for (int j = 0; j < vec[0].size(); j++) {
            os << vec[i][j];
        }
        os << endl;
    }
    return os;
}
template<typename V, typename H> void resize(vector<V> &vec, const H head) { vec.resize(head); }
template<typename V, typename H, typename ... T> void resize(vector<V> &vec, const H &head, const T ... tail) {
    vec.resize(head);
    for (auto &v: vec) resize(v, tail...);
}
template<typename T, typename _Pr> bool all_of(const vector<T> &vec, _Pr pred) { return std::all_of(vec.begin(), vec.end(), pred); }
template<typename T, typename _Pr> bool any_of(const vector<T> &vec, _Pr pred) { return std::any_of(vec.begin(), vec.end(), pred); }
template<typename T, typename _Pr> bool none_of(const vector<T> &vec, _Pr pred) { return std::none_of(vec.begin(), vec.end(), pred); }
template<typename T, typename _Pr> const typename vector<T>::const_iterator find_if(const vector<T> &vec, _Pr pred) { return std::find_if(vec.begin(), vec.end(), pred); }
template<typename T> bool contains(const vector<T> &vec, const T &val) { return std::find(vec.begin(), vec.end(), val) != vec.end(); }
template<typename T, typename _Pr> bool contains_if(const vector<T> &vec, _Pr pred) { return std::find_if(vec.begin(), vec.end(), pred) != vec.end(); }
template<class T, class U> void replace(vector<T> &a, T key, U v) { replace(a.begin(), a.end(), key, v); }
template<class T> void replace(str &a, T key, T v) { a.replace(all(a), key, v); }
//keyと同じかどうか01で置き換える
template<class T, class U> void replace(T &a, U k) { rep(i, sz(a)) a[i] = a[i] == k; }
template<class T, class U> void replace(T &a) { replace(a, '#'); }

template<class T> bool includes(vector<T> &a, vector<T> &b) {
    vi c = a;
    vi d = b;
    sort(all(c));
    sort(all(d));
    return includes(all(c), all(d));
}
template<class T> bool is_permutation(vector<T> &a, vector<T> &b) { return is_permutation(all(a), all(b)); }
template<class T> bool next_permutation(vector<T> &a) { return next_permutation(all(a)); }

template<class T> T pop(set<T> &set) {
    T res = *set.begin();
    set.erase(set.find(res));
    return res;
}
template<class T> T pop(mset<T> &set) {
    T res = *set.begin();
    set.erase(set.find(res));
    return res;
}
template<class T> T popBack(set<T> &set) {
    T res = *set.rbegin();
    set.erase(set.find(res));
    return res;
}
template<class T> T popBack(mset<T> &set) {
    T res = *set.rbegin();
    set.erase(set.find(res));
    return res;
}
inline void sort(string &a) { sort(a.begin(), a.end()); }
template<class T> inline void sort(vector<T> &a) { sort(a.begin(), a.end()); }
template<class T> inline void sort(vector<T> &a, int len) { sort(a.begin(), a.begin() + len); }
template<class T, class F> inline void sort(vector<T> &a, F f) { sort(a.begin(), a.end(), [&](T a, T b) { return f(a) < f(b); }); }
enum ___pcomparator {
    fisi, fisd, fdsi, fdsd, sifi, sifd, sdfi, sdfd
};
inline void sort(vector<P> &a, ___pcomparator type) {
    switch (type) {
        case fisi:
            sort(all(a), [&](P l, P r) { return l.fi != r.fi ? l.fi < r.fi : l.se < r.se; });
            break;
        case fisd:
            sort(all(a), [&](P l, P r) { return l.fi != r.fi ? l.fi < r.fi : l.se > r.se; });
            break;
        case fdsi:
            sort(all(a), [&](P l, P r) { return l.fi != r.fi ? l.fi > r.fi : l.se < r.se; });
            break;
        case fdsd:
            sort(all(a), [&](P l, P r) { return l.fi != r.fi ? l.fi > r.fi : l.se > r.se; });
            break;
        case sifi:
            sort(all(a), [&](P l, P r) { return l.se != r.se ? l.se < r.se : l.fi < r.fi; });
            break;
        case sifd:
            sort(all(a), [&](P l, P r) { return l.se != r.se ? l.se < r.se : l.fi > r.fi; });
            break;
        case sdfi:
            sort(all(a), [&](P l, P r) { return l.se != r.se ? l.se > r.se : l.fi < r.fi; });
            break;
        case sdfd:
            sort(all(a), [&](P l, P r) { return l.se != r.se ? l.se > r.se : l.fi > r.fi; });
            break;
    }
};
inline void sort(vector<T> &a, ___pcomparator type) {
    switch (type) {
        case fisi:
            sort(all(a), [&](T l, T r) { return l.f != r.f ? l.f < r.f : l.s < r.s; });
            break;
        case
            fisd:
            sort(all(a), [&](T l, T r) { return l.f != r.f ? l.f < r.f : l.s > r.s; });
            break;
        case
            fdsi:
            sort(all(a), [&](T l, T r) { return l.f != r.f ? l.f > r.f : l.s < r.s; });
            break;
        case
            fdsd:
            sort(all(a), [&](T l, T r) { return l.f != r.f ? l.f > r.f : l.s > r.s; });
            break;
        case
            sifi:
            sort(all(a), [&](T l, T r) { return l.s != r.s ? l.s < r.s : l.f < r.f; });
            break;
        case
            sifd:
            sort(all(a), [&](T l, T r) { return l.s != r.s ? l.s < r.s : l.f > r.f; });
            break;
        case
            sdfi:
            sort(all(a), [&](T l, T r) { return l.s != r.s ? l.s > r.s : l.f < r.f; });
            break;
        case
            sdfd:
            sort(all(a), [&](T l, T r) { return l.s != r.s ? l.s > r.s : l.f > r.f; });
            break;
    }
};
template<class T> inline void rsort(vector<T> &a) { sort(a.begin(), a.end(), greater<T>()); };
template<class T> inline void rsort(vector<T> &a, int len) { sort(a.begin(), a.begin() + len, greater<T>()); };
template<class U, class F> inline void rsort(vector<U> &a, F f) { sort(a.begin(), a.end(), [&](U l, U r) { return f(l) > f(r); }); };
template<class U> inline void sortp(vector<U> &a, vector<U> &b) {
    vp c;
    int n = sz(a);
    assert(n == sz(b));
    rep(i, n)c.eb(a[i], b[i]);
    sort(c);
    rep(i, n) {
        a[i] = c[i].first;
        b[i] = c[i].second;;
    }
};
//F = T<T>
//例えばreturn p.fi + p.se;
template<class U, class F> inline void sortp(vector<U> &a, vector<U> &b, F f) {
    vp c;
    int n = sz(a);
    assert(n == sz(b));
    rep(i, n)c.eb(a[i], b[i]);
    sort(c, f);
    rep(i, n) {
        a[i] = c[i].first;
        b[i] = c[i].second;
    }
};
template<class U, class F> inline void sortp(vector<U> &a, vector<U> &b, char type) {
    vp c;
    int n = sz(a);
    assert(n == sz(b));
    rep(i, n)c.eb(a[i], b[i]);
    sort(c, type);
    rep(i, n) {
        a[i] = c[i].first;
        b[i] = c[i].second;
    }
};
template<class U> inline void rsortp(vector<U> &a, vector<U> &b) {
    vp c;
    int n = sz(a);
    assert(n == sz(b));
    rep(i, n)c.eb(a[i], b[i]);
    rsort(c);
    rep(i, n) {
        a[i] = c[i].first;
        b[i] = c[i].second;
    }
};
template<class U, class F> inline void rsortp(vector<U> &a, vector<U> &b, F f) {
    vp c;
    int n = sz(a);
    assert(n == sz(b));
    rep(i, n)c.eb(a[i], b[i]);
    rsort(c, f);
    rep(i, n) {
        a[i] = c[i].first;
        b[i] = c[i].second;
    }
};
template<class U> inline void sortt(vector<U> &a, vector<U> &b, vector<U> &c) {
    vt r;
    int n = sz(a);
    assert(n == sz(b));
    assert(n == sz(c));
    rep(i, n)r.eb(a[i], b[i], c[i]);
    sort(r);
    rep(i, n) {
        a[i] = r[i].f;
        b[i] = r[i].s;
        c[i] = r[i].t;
    }
};
template<class U, class F> inline void sortt(vector<U> &a, vector<U> &b, vector<U> &c, F f) {
    vt r;
    int n = sz(a);
    assert(n == sz(b));
    assert(n == sz(c));
    rep(i, n)r.eb(a[i], b[i], c[i]);
    sort(r, f);
    rep(i, n) {
        a[i] = r[i].f;
        b[i] = r[i].s;
        c[i] = r[i].t;
    }
};
template<class U, class F> inline void rsortt(vector<U> &a, vector<U> &b, vector<U> &c, F f) {
    vt r;
    int n = sz(a);
    assert(n == sz(b));
    assert(n == sz(c));
    rep(i, n)r.eb(a[i], b[i], c[i]);
    rsort(r, f);
    rep(i, n) {
        a[i] = r[i].f;
        b[i] = r[i].s;
        c[i] = r[i].t;
    }
};
//indexをつけてsortする (second)
template<class T> auto sorti(vector<T> &a) {
    int n = sz(a);
    vp ret(n);
    rep(i, n)ret[i] = P(a[i], i);
    sort(ret);
    return ret;
};
template<class T> auto rsorti(vector<T> &a) {
    int n = sz(a);
    vp ret(n);
    rep(i, n)ret[i] = P(a[i], i);
    rsort(ret);
    return ret;
};
template<class T> inline void sort2(vector<vector<T>> &a) { for (int i = 0, n = a.size(); i < n; i++)sort(a[i]); }
template<class T> inline void rsort2(vector<vector<T>> &a) { for (int i = 0, n = a.size(); i < n; i++)rsort(a[i]); }
template<typename A, size_t N, typename T> inline void fill(A (&a)[N], const T &v) { rep(i, N)a[i] = v; }
template<typename A, size_t N, size_t O, typename T> inline void fill(A (&a)[N][O], const T &v) { rep(i, N)rep(j, O)a[i][j] = v; }
template<typename A, size_t N, size_t O, size_t P, typename T> inline void fill(A (&a)[N][O][P], const T &v) { rep(i, N)rep(j, O)rep(k, P)a[i][j][k] = v; }
template<typename A, size_t N, size_t O, size_t P, size_t Q, typename T> inline void fill(A (&a)[N][O][P][Q], const T &v) { rep(i, N)rep(j, O)rep(k, P)rep(l, Q)a[i][j][k][l] = v; }
template<typename A, size_t N, size_t O, size_t P, size_t Q, size_t R, typename T> inline void fill(A (&a)[N][O][P][Q][R], const T &v) { rep(i, N)rep(j, O)rep(k, P)rep(l, Q)rep(m, R)a[i][j][k][l][m] = v; }
template<typename A, size_t N, size_t O, size_t P, size_t Q, size_t R, size_t S, typename T> inline void fill(A (&a)[N][O][P][Q][R][S], const T &v) { rep(i, N)rep(j, O)rep(k, P)rep(l, Q)rep(m, R)rep(n, S)a[i][j][k][l][m][n] = v; }

template<typename A, size_t N> A sum(A (&a)[N]) {
    A res = 0;
    rep(i, N)res += a[i];
    return res;
}
template<typename A, size_t N, size_t O> A sum(A (&a)[N][O]) {
    A res = 0;
    rep(i, N)rep(j, O)res += a[i][j];
    return res;
}
template<typename A, size_t N, size_t O, size_t P> A sum(A (&a)[N][O][P]) {
    A res = 0;
    rep(i, N)rep(j, O)rep(k, P)res += a[i][j][k];
    return res;
}
template<typename A, size_t N, size_t O, size_t P, size_t Q> A sum(A (&a)[N][O][P][Q]) {
    A res = 0;
    rep(i, N)rep(j, O)rep(k, P)rep(l, Q)res += a[i][j][k][l];
    return res;
}
template<typename A, size_t N, size_t O, size_t P, size_t Q, size_t R> A sum(A (&a)[N][O][P][Q][R]) {
    A res = 0;
    rep(i, N)rep(j, O)rep(k, P)rep(l, Q)rep(m, R)res += a[i][j][k][l][m];
    return res;
}
template<typename A, size_t N, size_t O, size_t P, size_t Q, size_t R, size_t S> A sum(A (&a)[N][O][P][Q][R][S]) {
    A res = 0;
    rep(i, N)rep(j, O)rep(k, P)rep(l, Q)rep(m, R)rep(n, S)res += a[i][j][k][l][m][n];
    return res;
}

template<typename V, typename T>
void fill(V &xx, const T vall) {
    xx = vall;
}
template<typename V, typename T>
void fill(vector<V> &vecc, const T vall) {
    for (auto &&vx: vecc) fill(vx, vall);
}

//@汎用便利関数 入力
inline int in() {
    int ret;
//    scanf("%lld", &ret);
    cin >> ret;
    return ret;
}
inline string sin() {
    string ret;
    cin >> ret;
    return ret;
}
template<class T> inline void in(T &head) { cin >> head; }
template<class T, class... U> inline void in(T &head, U &... tail) {
    cin >> head;
    in(tail...);
}

#define _overloaddin(_1, _2, _3, _4, name, ...) name
#define din1(a) int a;cin>>a
#define din2(a, b) int a,b;cin>>a>> b
#define din3(a, b, c) int a,b,c;cin>>a>>b>>c
#define din4(a, b, c, d) int a,b,c,d;cin>>a>>b>>c>>d
#define din(...) _overloaddin(__VA_ARGS__,din4,din3,din2 ,din1)(__VA_ARGS__)

#define _overloaddind(_1, _2, _3, _4, name, ...) name
#define din1d(a) din1(a);a--
#define din2d(a, b) din2(a,b);a--,b--
#define din3d(a, b, c) din3(a,b,c);a--,b--,c--
#define din4d(a, b, c, d) din4(a,b,c,d);a--,b--,c--,d--
#define dind(...) _overloaddind(__VA_ARGS__,din4d,din3d,din2d ,din1d)(__VA_ARGS__)


#define _overloadout(_1, _2, _3, _4, name, ...) name
#define out1(a) cout<<a<<endl
#define out2(a, b) cout<<a<<" "<< b<<endl
#define out3(a, b, c) cout<<a<<" "<<b<<" "<<c<<endl
#define out4(a, b, c, d) cout<<a<<" "<<b<<" "<<c<<" "<<d<<endl
#define out(...) _overloadout(__VA_ARGS__,out4,out3,out2,out1)(__VA_ARGS__)

#define _overloadoutl(_1, _2, _3, _4, name, ...) name
#define outl1(a) cout<<a<<endl
#define outl2(a, b) cout<<a<<endl<<b<<endl
#define outl3(a, b, c) cout<<a<<endl<<b<<endl<<c<<endl
#define outl4(a, b, c, d) cout<<a<<endl<<b<<endl<<c<<endl<<d<<endl
#define outl(...) _overloadout(__VA_ARGS__,outl4,outl3,outl2,outl1)(__VA_ARGS__)

#define na(a, n) a.resize(n); rep(_i,n) cin >> a[_i];
#define nao(a, n) a.resize(n+1); rep(_i,n) cin >> a[_i+1];
#define nad(a, n) a.resize(n); rep(_i,n){ cin >> a[_i]; a[_i]--;}
#define na2(a, b, n) a.resize(n),b.resize(n);rep(_i, n)cin >> a[_i] >> b[_i];
#define nao2(a, b, n) a.resize(n+1),b.resize(n+1);rep(_i, n)cin >> a[_i+1] >> b[_i+1];
#define na2d(a, b, n) a.resize(n),b.resize(n);rep(_i, n){cin >> a[_i] >> b[_i];a[_i]--,b[_i]--;}
#define na3(a, b, c, n) a.resize(n),b.resize(n),c.resize(n);   rep(_i, n)cin >> a[_i] >> b[_i] >> c[_i];
#define na3d(a, b, c, n) a.resize(n),b.resize(n),c.resize(n);   rep(_i, n){cin >> a[_i] >> b[_i] >> c[_i];a[_i]--,b[_i]--,c[_i]--;}
#define nt(a, h, w) resize(a,h,w);rep(_hi,h)rep(_wi,w) cin >> a[_hi][_wi];
#define ntd(a, h, w) rs(a,h,w);rep(_hi,h)rep(_wi,w) cin >> a[_hi][_wi], a[_hi][_wi]--;
#define ntp(a, h, w) fill(a,'#');rep(_hi,1,h+1)rep(_wi,1,w+1) cin >> a[_hi][_wi];

//デバッグ
#define sp << " " <<

#define debugName(VariableName) # VariableName

#define _deb1(x)  debugName(x)<<" = "<<x
#define _deb2(x, ...) _deb1(x) <<", "<< _deb1(__VA_ARGS__)
#define _deb3(x, ...) _deb1(x) <<", "<< _deb2(__VA_ARGS__)
#define _deb4(x, ...) _deb1(x) <<", "<< _deb3(__VA_ARGS__)
#define _deb5(x, ...) _deb1(x) <<", "<< _deb4(__VA_ARGS__)
#define _deb6(x, ...) _deb1(x) <<", "<< _deb5(__VA_ARGS__)
#define _deb7(x, ...) _deb1(x) <<", "<< _deb6(__VA_ARGS__)
#define _deb8(x, ...) _deb1(x) <<", "<< _deb7(__VA_ARGS__)
#define _deb9(x, ...) _deb1(x) <<", "<< _deb8(__VA_ARGS__)
#define _deb10(x, ...) _deb1(x) <<", "<< _deb9(__VA_ARGS__)

#define _overloadebug(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, name, ...) name

#ifdef _DEBUG
#define deb(...)  cerr<< _overloadebug(__VA_ARGS__,_deb10,_deb9,_deb8,_deb7,_deb6,_deb5,_deb4,_deb3,_deb2,_deb1)(__VA_ARGS__) <<endl
#else
#define deb(...) ;
#endif

#define debugline(x) cerr << x << " " << "(L:" << __LINE__ << ")" << '\n'

//よく使うクラス、構造体
class UnionFind {
public:
    vi par, rank, sizes;
    int n, trees;
    UnionFind(int n) : n(n), trees(n) {
        par.resize(n), rank.resize(n), sizes.resize(n);
        rep(i, n)par[i] = i, sizes[i] = 1;
    }
    int root(int x) {
        if (par[x] == x)return x;
        else return par[x] = root(par[x]);
    }
    int find(int x) { return root(x); }
    void unite(int x, int y) {
        x = root(x);
        y = root(y);
        if (x == y)return;
        if (rank[x] < rank[y])swap(x, y);
        trees--;
        par[y] = x;
        sizes[x] += sizes[y];
        if (rank[x] == rank[y])rank[x]++;
    }
    bool same(int x, int y) { return root(x) == root(y); }
    int size(int x) { return sizes[root(x)]; }
    //順不同 umapなので
    vec<vi> sets() {
        vec<vi> res(trees);
        umap<int, vi> map;
        rep(i, n) map[root(i)].push_back(i);
        int i = 0;
        for (auto &&p:map) {
            int r = p.fi;
            res[i].push_back(r);
            for (auto &&v:p.se) {
                if (r == v)continue;
                res[i].push_back(v);
            }
            i++;
        }
        return res;
    }
};

using bint =__int128;
using u32 = unsigned int;
using u64 = unsigned long long;
using u128 = __uint128_t;
std::ostream &operator<<(std::ostream &dest, __int128_t value) {
    std::ostream::sentry s(dest);
    if (s) {
        __uint128_t tmp = value < 0 ? -value : value;
        char buffer[128];
        char *d = std::end(buffer);
        do {
            --d;
            *d = "0123456789"[tmp % 10];
            tmp /= 10;
        } while (tmp != 0);
        if (value < 0) {
            --d;
            *d = '-';
        }
        int len = std::end(buffer) - d;
        if (dest.rdbuf()->sputn(d, len) != len) {
            dest.setstate(std::ios_base::badbit);
        }
    }
    return dest;
}

__int128 toi128(string &s) {
    __int128 ret = 0;
    for (int i = 0; i < s.length(); i++)
        if ('0' <= s[i] && s[i] <= '9')
            ret = 10 * ret + s[i] - '0';
    return ret;
}


template<typename T> T minv(T a, T m);
template<typename T> T minv(T a);

template<typename T>
class Modular {
public:
    using Type = typename decay<decltype(T::value)>::type;

    constexpr Modular() : value() {}
    template<typename U>
    Modular(const U &x) {
        value = normalize(x);
    }

    template<typename U>
    static Type normalize(const U &x) {
        Type v;
        if (-mod() <= x && x < mod()) v = static_cast<Type>(x);
        else v = static_cast<Type>(x % mod());
        if (v < 0) v += mod();
        return v;
    }

    const Type &operator()() const { return value; }
    template<typename U>
    explicit operator U() const { return static_cast<U>(value); }
    constexpr static Type mod() { return T::value; }

    Modular &operator+=(const Modular &other) {
        if ((value += other.value) >= mod()) value -= mod();
        return *this;
    }
    Modular &operator-=(const Modular &other) {
        if ((value -= other.value) < 0) value += mod();
        return *this;
    }
    template<typename U> Modular &operator+=(const U &other) { return *this += Modular(other); }
    template<typename U> Modular &operator-=(const U &other) { return *this -= Modular(other); }
    Modular &operator++() { return *this += 1; }
    Modular &operator--() { return *this -= 1; }
    Modular operator++(signed) {
        Modular result(*this);
        *this += 1;
        return result;
    }
    Modular operator--(signed) {
        Modular result(*this);
        *this -= 1;
        return result;
    }
    Modular operator-() const { return Modular(-value); }

    template<typename U = T>
    typename enable_if<is_same<typename Modular<U>::Type, signed>::value, Modular>::type &operator*=(const Modular &rhs) {
#ifdef _WIN32
        uint64_t x = static_cast<int64_t>(value) * static_cast<int64_t>(rhs.value);
        uint32_t xh = static_cast<uint32_t>(x >> 32), xl = static_cast<uint32_t>(x), d, m;
        asm(
        "divl %4; \n\t"
        : "=a" (d), "=d" (m)
        : "d" (xh), "a" (xl), "r" (mod())
        );
        value = m;
#else
        value = normalize(static_cast<int64_t>(value) * static_cast<int64_t>(rhs.value));
#endif
        return *this;
    }
    template<typename U = T>
    typename enable_if<is_same<typename Modular<U>::Type, int64_t>::value, Modular>::type &operator*=(const Modular &rhs) {
        int64_t q = static_cast<int64_t>(static_cast<double>(value) * rhs.value / mod());
        value = normalize(value * rhs.value - q * mod());
        return *this;
    }
    template<typename U = T>
    typename enable_if<!is_integral<typename Modular<U>::Type>::value, Modular>::type &operator*=(const Modular &rhs) {
        value = normalize(value * rhs.value);
        return *this;
    }
    Modular &operator/=(const Modular &other) { return *this *= Modular(minv(other.value)); }
    template<typename U> friend bool operator==(const Modular<U> &lhs, const Modular<U> &rhs);
    template<typename U> friend bool operator<(const Modular<U> &lhs, const Modular<U> &rhs);
    template<typename U> friend std::istream &operator>>(std::istream &stream, Modular<U> &number);
    operator int() { return normalize(value); }
private:
    Type value;
};

template<typename T> bool operator==(const Modular<T> &lhs, const Modular<T> &rhs) { return lhs.value == rhs.value; }
template<typename T, typename U> bool operator==(const Modular<T> &lhs, U rhs) { return lhs == Modular<T>(rhs); }
template<typename T, typename U> bool operator==(U lhs, const Modular<T> &rhs) { return Modular<T>(lhs) == rhs; }

template<typename T> bool operator!=(const Modular<T> &lhs, const Modular<T> &rhs) { return !(lhs == rhs); }
template<typename T, typename U> bool operator!=(const Modular<T> &lhs, U rhs) { return !(lhs == rhs); }
template<typename T, typename U> bool operator!=(U lhs, const Modular<T> &rhs) { return !(lhs == rhs); }

template<typename T> bool operator<(const Modular<T> &lhs, const Modular<T> &rhs) { return lhs.value < rhs.value; }

template<typename T> Modular<T> operator+(const Modular<T> &lhs, const Modular<T> &rhs) { return Modular<T>(lhs) += rhs; }
template<typename T, typename U> Modular<T> operator+(const Modular<T> &lhs, U rhs) { return Modular<T>(lhs) += rhs; }
template<typename T, typename U> Modular<T> operator+(U lhs, const Modular<T> &rhs) { return Modular<T>(lhs) += rhs; }

template<typename T> Modular<T> operator-(const Modular<T> &lhs, const Modular<T> &rhs) { return Modular<T>(lhs) -= rhs; }
template<typename T, typename U> Modular<T> operator-(const Modular<T> &lhs, U rhs) { return Modular<T>(lhs) -= rhs; }
template<typename T, typename U> Modular<T> operator-(U lhs, const Modular<T> &rhs) { return Modular<T>(lhs) -= rhs; }

template<typename T> Modular<T> operator*(const Modular<T> &lhs, const Modular<T> &rhs) { return Modular<T>(lhs) *= rhs; }
template<typename T, typename U> Modular<T> operator*(const Modular<T> &lhs, U rhs) { return Modular<T>(lhs) *= rhs; }
template<typename T, typename U> Modular<T> operator*(U lhs, const Modular<T> &rhs) { return Modular<T>(lhs) *= rhs; }

template<typename T> Modular<T> operator/(const Modular<T> &lhs, const Modular<T> &rhs) { return Modular<T>(lhs) /= rhs; }
template<typename T, typename U> Modular<T> operator/(const Modular<T> &lhs, U rhs) { return Modular<T>(lhs) /= rhs; }
template<typename T, typename U> Modular<T> operator/(U lhs, const Modular<T> &rhs) { return Modular<T>(lhs) /= rhs; }

constexpr signed MOD = 998244353;//MOD
using mint = Modular<std::integral_constant<decay<decltype(MOD)>::type, MOD>>;

constexpr int mint_len = 1400001;
vi fac, finv, inv, p2;
void setmod() {
    fac.resize(mint_len);
    finv.resize(mint_len);
    inv.resize(mint_len);
    p2.resize(mint_len);
    inv[1] = fac[0] = finv[0] = p2[0] = 1;
    for (int i = 2; i < mint_len; ++i) inv[i] = inv[MOD % i] * (MOD - MOD / i) % MOD;
    for (int i = 1; i < mint_len; ++i) fac[i] = fac[i - 1] * i % MOD, finv[i] = finv[i - 1] * inv[i] % MOD, p2[i] = p2[i - 1] * 2 % MOD;
}
#define smod setmod
inline mint com(int n, int r) {
    if (r < 0 || r > n) return 0;
    return mint(finv[r] * fac[n] % MOD * finv[n - r]);
}
inline mint pom(int n, int r) {
//    if (!sz(fac)) com(0, -1);
    if (r < 0 || r > n) return 0;
    return mint(fac[n] * finv[n - 1]);
}
inline mint npr(int n, int r) {
//    if (!sz(fac)) com(0, -1);
    if (r < 0 || r > n) return 0;
    return mint(fac[n] * finv[n - r]);
}
inline int nprin(int n, int r) {
//    if (!sz(fac)) com(0, -1);
    if (r < 0 || r > n) return 0;
    return fac[n] * finv[n - r] % MOD;
}

inline int icom(int n, int r) {
    const int NUM_ = 1400001;
    static ll fac[NUM_ + 1], finv[NUM_ + 1], inv[NUM_ + 1];
    if (fac[0] == 0) {
        inv[1] = fac[0] = finv[0] = 1;
        for (int i = 2; i <= NUM_; ++i) inv[i] = inv[MOD % i] * (MOD - MOD / i) % MOD;
        for (int i = 1; i <= NUM_; ++i) fac[i] = fac[i - 1] * i % MOD, finv[i] = finv[i - 1] * inv[i] % MOD;
    }
    if (r < 0 || r > n) return 0;
    return ((finv[r] * fac[n] % MOD) * finv[n - r]) % MOD;
}
#define ncr com
#define ncri icom
inline mint nhr(int n, int r) { return com(n + r - 1, r); }
inline mint hom(int n, int r) { return com(n + r - 1, r); }
inline int nhri(int n, int r) { return icom(n + r - 1, r); }


template<typename T> inline T minv(T a, T m) {
    T u = 0, v = 1;
    while (a != 0) {
        T t = m / a;
        m -= t * a;
        swap(a, m);
        u -= t * v;
        swap(u, v);
    }
    assert(m == 1);
    return u;
}
template<typename T> inline T minv(T a) {
    if (a < mint_len)return inv[a];
    T u = 0, v = 1;
    int m = MOD;
    while (a != 0) {
        T t = m / a;
        m -= t * a;
        swap(a, m);
        u -= t * v;
        swap(u, v);
    }
    assert(m == 1);
    return u;
}

template<typename T, typename U> inline Modular<T> mpow(const Modular<T> &a, const U &b) {
    assert(b >= 0);
    int x = a(), res = 1;
    U p = b;
    while (p > 0) {
        if (p & 1) (res *= x) %= MOD;
        (x *= x) %= MOD;
        p >>= 1;
    }
    return res;
}
template<typename T, typename U, typename V> inline mint mpow(const T a, const U b, const V m = MOD) {
    assert(b >= 0);
    int x = a, res = 1;
    U p = b;
    while (p > 0) {
        if (p & 1) (res *= x) %= m;
        (x *= x) %= m;
        p >>= 1;
    }
    return res;
}
template<typename T, typename U> inline mint mpow(const T a, const U b) {
    assert(b >= 0);
    int x = a, res = 1;
    U p = b;
    while (p > 0) {
        if (p & 1) (res *= x) %= MOD;
        (x *= x) %= MOD;
        p >>= 1;
    }
    return res;
}
template<typename T, typename U, typename V> inline int mpowi(const T &a, const U &b, const V &m = MOD) {
    assert(b >= 0);
    int x = a, res = 1;
    U p = b;
    while (p > 0) {
        if (p & 1) (res *= x) %= m;
        (x *= x) %= m;
        p >>= 1;
    }
    return res;
}
template<typename T> inline string to_string(const Modular<T> &number) {
    return to_string(number());
}

template<typename T> std::ostream &operator<<(std::ostream &stream, const Modular<T> &number) {
    return stream << number();
}

template<typename T> std::istream &operator>>(std::istream &stream, Modular<T> &number) {
    typename common_type<typename Modular<T>::Type, int64_t>::type x;
    stream >> x;
    number.value = Modular<T>::normalize(x);
    return stream;
}

using PM = pair<mint, mint>;
using vm = vector<mint>;
using mapm = map<int, mint>;
using umapm = umap<int, mint>;
#define _overloadvvm(_1, _2, _3, _4, name, ...) name
#define vnm(name, ...) auto name = make_v<mint>(__VA_ARGS__)

#define vvm1(a) vec<vm> a
#define vvm2(a, b) vec<vm> a(b)
#define vvm3(a, b, c) vec<vm> a(b,vm(c))
#define vvm4(a, b, c, d) vec<vm> a(b,vm(c,d))
#define vvm(...) _overloadvvm(__VA_ARGS__,vvm4,vvm3,vvm2 ,vvm1)(__VA_ARGS__)

//エラー
void ole() {
#ifdef _DEBUG
    debugline("ole");
    exit(0);
#endif
    string a = "a";
    rep(i, 30)a += a;
    rep(i, 1 << 17)cout << a << endl;
    cout << "OLE 出力長制限超過" << endl;
    exit(0);
}
void tle() { while (inf)cout << inf << endl; }

//便利関数

//テスト用
char ranc() {
    return (char) ('a' + rand() % 26);
}
int rand(int min, int max) {
    assert(min <= max);
    if (min >= 0 && max >= 0) {
        return rand() % (max + 1 - min) + min;
    } else if (max < 0) {
        return -rand(-max, -min);
    } else {
        //+
        if (rand() % 2) {
            return rand(0, max);
            //-
        } else {
            return -rand(0, -min);
        }
    }
}
vi ranv(int n, int min, int max) {
    vi v(n);
    rep(i, n)v[i] = rand(min, max);
    return v;
}
//単調増加
vi ranvi(int n, int min, int max) {
    vi v(n);
    bool bad = 1;
    while (bad) {
        bad = 0;
        v.resize(n);
        rep(i, n) {
            if (i && min > max - v[i - 1]) {
                bad = 1;
                break;
            }
            if (i)v[i] = v[i - 1] + rand(min, max - v[i - 1]);
            else v[i] = rand(min, max);
        }
    }
    return v;
}
void ranvlr(int n, int min, int max, vi &l, vi &r) {
    l.resize(n);
    r.resize(n);
    rep(i, n) {
        l[i] = rand(min, max);
        r[i] = l[i] + rand(0, max - l[i]);
    }
}
//便利 汎用
vp run_length(vi &a) {
    vp ret;
    ret.eb(a[0], 1);
    rep(i, 1, sz(a)) {
        if (ret.back().fi == a[i]) {
            ret.back().se++;
        } else {
            ret.eb(a[i], 1);
        }
    }
    return ret;
}
vector<pair<char, int>> run_length(string &a) {
    vector<pair<char, int>> ret;
    ret.eb(a[0], 1);
    rep(i, 1, sz(a)) {
        if (ret.back().fi == a[i]) {
            ret.back().se++;
        } else {
            ret.eb(a[i], 1);
        }
    }
    return ret;
}
template<class F> inline int mgr(int ok, int ng, F f) {
#define _mgrbody int mid = (ok + ng) / 2; if (f(mid))ok = mid; else ng = mid;
    if (ok < ng)while (ng - ok > 1) { _mgrbody } else while (ok - ng > 1) { _mgrbody }
    return ok;
}

template<class F> inline int mgr(int ok, int ng, int second, F f) {
#define _mgrbody2 int mid = (ok + ng) / 2; if (f(mid, second))ok = mid; else ng = mid;
    if (ok < ng) while (ng - ok > 1) { _mgrbody2 } else while (ok - ng > 1) { _mgrbody2 }
    return ok;
}
//strを整数として比較
string smax(str &a, str b) {
    if (sz(a) < sz(b)) {
        return b;
    } else if (sz(a) > sz(b)) {
        return a;
    } else {
        rep(i, sz(a)) {
            if (a[i] < b[i]) {
                return b;
            } else if (a[i] > b[i])return a;
        }
    }
    return a;
}
//strを整数として比較
string smin(str &a, str b) {
    if (sz(a) < sz(b)) {
        return a;
    } else if (sz(a) > sz(b)) {
        return b;
    } else {
        rep(i, sz(a)) {
            if (a[i] < b[i]) {
                return a;
            } else if (a[i] > b[i])return b;
        }
    }
    return a;
}
template<typename V, typename T> int find(vector<V> &a, const T key) {
    rep(i, sz(a))if (a[i] == key)return i;
    return -1;
}
template<typename V, typename T> P find(vector<vector<V>> &a, const T key) {
    rep(i, sz(a)) rep(j, sz(a[0]))if (a[i][j] == key)return mp(i, j);
    return mp(-1, -1);
}
template<typename V, typename U> T find(vector<vector<vector<V>>> &a, const U key) {
    rep(i, sz(a))rep(j, sz(a[0]))rep(k, sz(a[0][0]))if (a[i][j][k] == key)return mt(i, j, k);
    return mt(-1, -1, -1);
}

template<typename V, typename T> int count(V &a, const T k) { return a == k; }
template<typename V, typename T> int count(vector<V> &a, const T k) {
    int ret = 0;
    fora(v, a)ret += count(v, k);
    return ret;
}
template<typename T> int count(char &a, const T k) { return a == k; }
template<typename T> int count(str &a, const T k) {
    int ret = 0;
    rep(i, sz(a))ret += count(a[i], k);
    return ret;
}
template<typename V> int count_odd(V &a) { return a % 2; }
template<typename V> int count_odd(vector<V> &a) {
    int ret = 0;
    fora(v, a)ret += count_odd(v);
    return ret;
}
template<typename V> int count_even(V &a) { return a % 2 == 0; }
template<typename V> int count_even(vector<V> &a) {
    int ret = 0;
    fora(v, a)ret += count_even(v);
    return ret;
}
//algorythm
void iota(vector<int> &ve, int s, int n) {
    ve.resize(n);
    iota(all(ve), s);
}
vi iota(int s, int n) {
    vi ve(n);
    iota(all(ve), s);
    return ve;
}

inline ll rev(ll a) {
    ll res = 0;
    while (a) {
        res *= 10;
        res += a % 10;
        a /= 10;
    }
    return res;
}
template<class T> inline void rev(vector<T> &a) {
    reverse(all(a));
}
void inline rev(string &a) {
    reverse(all(a));
}

int get(int a, int keta) { return (a / (int) pow(10, keta)) % 10; }
int keta(int v) {
    int cou = 0;
    while (v) { cou++, v %= 10; }
    return cou;
}
int dsum(int v) {
    int ret = 0;
    for (; v; v /= 10)ret += v % 10;
    return ret;
}
inline int sumd(int v) { return dsum(v); }
//変換系
template<class T, class U> inline vector<T> keys(vector<pair<T, U>> a) {
    vector<T> res;
    for (auto &&k :a)res.pb(k.fi);
    return res;
}
template<class T, class U> inline vector<U> keys(map<T, U> a) {
    vector<U> res;
    for (auto &&k :a)res.pb(k.fi);
    return res;
}

template<class T, class U> inline vector<U> keys(umap<T, U> a) {
    vector<U> res;
    for (auto &&k :a)res.pb(k.fi);
    return res;
}

template<class T, class U> vector<U> inline values(vector<pair<T, U>> a) {
    vector<U> res;
    for (auto &&k :a)res.pb(k.se);
    return res;
}

template<class T, class U> inline vector<T> values(map<T, U> a) {
    vector<T> res;
    for (auto &&k :a)res.pb(k.se);
    return res;
}

template<class T, class U> inline vector<T> values(umap<T, U> a) {
    vector<T> res;
    for (auto &&k :a)res.pb(k.se);
    return res;
}

template<class T, class U> inline bool chma(T &a, const U &b) {
    if (a < b) {
        a = b;
        return true;
    }
    return false;
}
template<class U> inline bool chma(const U &b) { return chma(ma, b); }
template<class T, class U> inline bool chmi(T &a, const U &b) {
    if (b < a) {
        a = b;
        return true;
    }
    return false;
}
template<class U> inline bool chmi(const U &b) { return chmi(mi, b); }
template<class T> inline T min(T a, signed b) { return a < b ? a : b; }
template<class T> inline T max(T a, signed b) { return a < b ? b : a; }
template<class T> inline T min(T a, T b, T c) { return a >= b ? b >= c ? c : b : a >= c ? c : a; }
template<class T> inline T max(T a, T b, T c) { return a <= b ? b <= c ? c : b : a <= c ? c : a; }
template<class T> inline T min(vector<T> a) { return *min_element(all(a)); }
template<class T> inline T mini(vector<T> a) { return min_element(all(a)) - a.begin(); }
template<class T> inline T min(vector<T> a, int n) { return *min_element(a.begin(), a.begin() + min(n, sz(a))); }
template<class T> inline T min(vector<T> a, int s, int n) { return *min_element(a.begin() + s, a.begin() + min(n, sz(a))); }
template<class T> inline T max(vector<T> a) { return *max_element(all(a)); }
template<class T> inline T maxi(vector<T> a) { return max_element(all(a)) - a.begin(); }
template<class T> inline T max(vector<T> a, int n) { return *max_element(a.begin(), a.begin() + min(n, sz(a))); }
template<class T> inline T max(vector<T> a, int s, int n) { return *max_element(a.begin() + s, a.begin() + min(n, sz(a))); }
template<typename A, size_t N> inline A max(A (&a)[N]) {
    A res = a[0];
    rep(i, N)res = max(res, a[i]);
    return res;
}
template<typename A, size_t N, size_t O> inline A max(A (&a)[N][O]) {
    A res = max(a[0]);
    rep(i, N)res = max(res, max(a[i]));
    return res;
}
template<typename A, size_t N, size_t O, size_t P> inline A max(A (&a)[N][O][P]) {
    A res = max(a[0]);
    rep(i, N)res = max(res, max(a[i]));
    return res;
}
template<typename A, size_t N, size_t O, size_t P, size_t Q> inline A max(A (&a)[N][O][P][Q], const T &v) {
    A res = max(a[0]);
    rep(i, N)res = max(res, max(a[i]));
    return res;
}
template<typename A, size_t N, size_t O, size_t P, size_t Q, size_t R> inline A max(A (&a)[N][O][P][Q][R]) {
    A res = max(a[0]);
    rep(i, N)res = max(res, max(a[i]));
    return res;
}
template<typename A, size_t N, size_t O, size_t P, size_t Q, size_t R, size_t S> inline A max(A (&a)[N][O][P][Q][R][S]) {
    A res = max(a[0]);
    rep(i, N)res = max(res, max(a[i]));
    return res;
}
template<typename A, size_t N> inline A min(A (&a)[N]) {
    A res = a[0];
    rep(i, N)res = min(res, a[i]);
    return res;
}
template<typename A, size_t N, size_t O> inline A min(A (&a)[N][O]) {
    A res = min(a[0]);
    rep(i, N)res = min(res, max(a[i]));
    return res;
}
template<typename A, size_t N, size_t O, size_t P> inline A min(A (&a)[N][O][P]) {
    A res = min(a[0]);
    rep(i, N)res = min(res, min(a[i]));
    return res;
}
template<typename A, size_t N, size_t O, size_t P, size_t Q> inline A min(A (&a)[N][O][P][Q], const T &v) {
    A res = min(a[0]);
    rep(i, N)res = min(res, min(a[i]));
    return res;
}
template<typename A, size_t N, size_t O, size_t P, size_t Q, size_t R> inline A min(A (&a)[N][O][P][Q][R]) {
    A res = min(a[0]);
    rep(i, N)res = min(res, min(a[i]));
    return res;
}
template<typename A, size_t N, size_t O, size_t P, size_t Q, size_t R, size_t S> inline A min(A (&a)[N][O][P][Q][R][S]) {
    A res = min(a[0]);
    rep(i, N)res = min(res, min(a[i]));
    return res;
}


template<class T> inline T sum(T &v) { return v; }
template<class T> inline auto sum(vector<T> &v) {
    auto ret = sum(v[0]);
    rep(i, 1, sz(v))ret += sum(v[i]);
    return ret;
}
template<class T, class U, class... V> inline auto sum(vector<T> &v, U head, V... tail) {
    auto ret = sum(v[0], tail...);
    rep(i, 1, min(sz(v), head))ret += sum(v[i], tail...);
    return ret;
}

inline P sump(vp &v, int len = -1) {
    if (len == -1)len = v.size();
    P res = {0, 0};
    chmi(len, v.size());
    rep(i, len) {
        res.fi += v[i].fi;
        res.se += v[i].se;
    }
    return res;
}

///要素が0の時、返り値は0か1か
template<class T> inline T mul(vector<T> &v, int len = -1) {
    if (len == -1)len = v.size();
    T res = 1;
    chmi(len, v.size());
    rep(i, len)res *= v[i];
    return res;
}
inline void clear(PQ &q) { while (q.size())q.pop(); }
template<class T> inline void clear(queue<T> &q) { while (q.size())q.pop(); }
template<class T> inline T *negarr(int size) {
    T *body = (T *) malloc((size * 2 + 1) * sizeof(T));
    return body + size;
}
template<class T> inline T *negarr2(int h, int w) {
    double **dummy1 = new double *[2 * h + 1];
    double *dummy2 = new double[(2 * h + 1) * (2 * w + 1)];
    dummy1[0] = dummy2 + w;
    for (int i = 1; i <= 2 * h + 1; i++) {
        dummy1[i] = dummy1[i - 1] + 2 * w + 1;
    }
    double **a = dummy1 + h;
}
//imoは0-indexed
//ruiは1-indexed
template<class T> inline vector<T> imo(vector<T> &v) {
    vector<T> ret = v;
    rep(i, sz(ret) - 1)ret[i + 1] += ret[i];
    return ret;
}
//漸化的に最小を持つ
template<class T> inline vector<T> imi(vector<T> &v) {
    vector<T> ret = v;
    rep(i, sz(ret) - 1)chmi(ret[i + 1], ret[i]);
    return ret;
}
template<class T> struct ruiC {
    const vector<T> rui;
    ruiC(vector<T> &ru) : rui(ru) {}
    inline T operator()(int l, int r) {
        assert(l <= r);
        return rui[r] - rui[l];
    }
    inline T operator[](int i) {
        return rui[i];
    }
};

template<class T> struct rruic {
    const T *rrui;
    rruic(T *ru) : rrui(ru) {}
    //n-1から-1へ
    inline T operator()(int l, int r) {
        assert(l >= r);
        return rrui[r] - rrui[l];
    }
    inline T operator[](int i) { return rrui[i]; }
};
template<class T> inline vector<T> ruiv(vector<T> &a) {
    vector<T> ret(a.size() + 1);
    rep(i, a.size())ret[i + 1] = ret[i] + a[i];
    return ret;
}
template<class T> inline ruiC<T> ruic(vector<T> &a) {
    vector<T> ret = ruiv(a);
    return ruiC<T>(ret);
}
//kと同じものの数
template<class T, class U> inline vi ruiv(T &a, U k) {
    vi ret(a.size() + 1);
    rep(i, a.size())ret[i + 1] = ret[i] + (a[i] == k);
    return ret;
}
template<class T, class U> inline ruiC<int> ruic(T &a, U k) {
    vi ret = ruiv(a, k);
    return ruiC<int>(ret);
}

//xor
template<class T> inline vector<T> ruix(vector<T> &a) {
    vector<T> ret(a.size() + 1);
    rep(i, a.size())ret[i + 1] = ret[i] ^ a[i];
    return ret;
}
template<class T> inline vector<T> ruim(vector<T> &a) {
    vector<T> res(a.size() + 1, 1);
    rep(i, a.size())res[i + 1] = res[i] * a[i];
    return res;
}
//漸化的に最小を1indexで持つ
template<class T> inline vector<T> ruimi(vector<T> &a) {
    int n = sz(a);
    vector<T> ret(n + 1);
    rep(i, 1, n) {
        ret[i] = a[i - 1];
        chmi(ret[i + 1], ret[i]);
    }
    return ret;
}
//template<class T> T *rrui(vector<T> &a) {
//右から左にかけての半開区間 (-1 n-1]
template<class T> rruic<T> rrui(vector<T> &a) {
    int len = a.size();
    T *body = (T *) malloc((len + 1) * sizeof(T));
    T *res = body + 1;
    rer(i, len - 1)res[i - 1] = res[i] + a[i];
    return rruic<T>(res);
}
//掛け算
template<class T> T *rruim(vector<T> &a) {
    int len = a.size();
    T *body = (T *) malloc((len + 1) * sizeof(T));
    T *res = body + 1;
    res[len - 1] = 1;
    rer(i, len - 1)res[i - 1] = res[i] * a[i];
    return res;
}
template<class T, class U> void inc(T &a, U v = 1) { a += v; }
template<class T, class U> void inc(vector<T> &a, U v = 1) { for (auto &u :a)inc(u, v); }
template<class T> void inc(vector<T> &a) { for (auto &u :a)inc(u, 1); }
template<class T, class U> void plus(T &a, U v = 1) { a += v; }
template<class T, class U> void plus(vector<T> &a, U v = 1) { for (auto &u :a)inc(u, v); }
template<class T> void plus(vector<T> &a) { for (auto &u :a)inc(u, 1); }
template<class T, class U> void dec(T &a, U v = 1) { a -= v; }
template<class T, class U> void dec(vector<T> &a, U v = 1) { for (auto &u :a)dec(u, v); }
template<class T> void dec(vector<T> &a) { for (auto &u :a)dec(u, 1); }
template<class T, class U> void minu(T &a, U v = 1) { a -= v; }
template<class T, class U> void minu(vector<T> &a, U v = 1) { for (auto &u :a)dec(u, v); }
template<class T> void minu(vector<T> &a) { for (auto &u :a)dec(u, 1); }
template<class U> void minu(string &a, U v = 1) { for (auto &u :a)dec(u, v); }
void minu(string &a) { for (auto &u :a)dec(u, 1); }
inline bool inside(int h, int w, int H, int W) { return h >= 0 && w >= 0 && h < H && w < W; }
inline bool inside(int l, int v, int r) { return l <= v && v < r; }
#define ins inside
ll u(ll a) { return a < 0 ? 0 : a; }
template<class T> vector<T> u(const vector<T> &a) {
    vector<T> ret = a;
    fora(v, ret)v = u(v);
    return ret;
}
#define MIN(a) numeric_limits<a>::min()
#define MAX(a) numeric_limits<a>::max()
ll goldd(ll left, ll right, function<ll(ll)> calc) {
    double GRATIO = 1.6180339887498948482045868343656;
    ll lm = left + (ll) ((right - left) / (GRATIO + 1.0));
    ll rm = lm + (ll) ((right - lm) / (GRATIO + 1.0));
    ll fl = calc(lm);
    ll fr = calc(rm);
    while (right - left > 10) {
        if (fl < fr) {
            right = rm;
            rm = lm;
            fr = fl;
            lm = left + (ll) ((right - left) / (GRATIO + 1.0));
            fl = calc(lm);
        } else {
            left = lm;
            lm = rm;
            fl = fr;
            rm = lm + (ll) ((right - lm) / (GRATIO + 1.0));
            fr = calc(rm);
        }
    }
    ll minScore = MAX(ll);
    ll resIndex = left;
    for (ll i = left; i < right + 1; i++) {
        ll score = calc(i);
        if (minScore > score) {
            minScore = score;
            resIndex = i;
        }
    }
    return resIndex;
}
ll goldt(ll left, ll right, function<ll(ll)> calc) {
    double GRATIO = 1.6180339887498948482045868343656;
    ll lm = left + (ll) ((right - left) / (GRATIO + 1.0));
    ll rm = lm + (ll) ((right - lm) / (GRATIO + 1.0));
    ll fl = calc(lm);
    ll fr = calc(rm);
    while (right - left > 10) {
        if (fl > fr) {
            right = rm;
            rm = lm;
            fr = fl;
            lm = left + (ll) ((right - left) / (GRATIO + 1.0));
            fl = calc(lm);
        } else {
            left = lm;
            lm = rm;
            fl = fr;
            rm = lm + (ll) ((right - lm) / (GRATIO + 1.0));
            fr = calc(rm);
        }
    }
    if (left > right) {
        ll l = left;
        left = right;
        right = l;
    }
    ll maxScore = MIN(ll);
    ll resIndex = left;
    for (ll i = left; i < right + 1; i++) {
        ll score = calc(i);
        if (maxScore < score) {
            maxScore = score;
            resIndex = i;
        }
    }
    return resIndex;
}
template<class T> T min(vector<vector<T>> &a) {
    T res = MAX(T);
    rep(i, a.size())chmi(res, *min_element(all(a[i])));
    return res;
}
template<class T> T max(vector<vector<T>> &a) {
    T res = MIN(T);
    rep(i, a.size())chma(res, *max_element(all(a[i])));
    return res;
}

constexpr bool bget(ll m, int keta) {
    assert(keta < 64);
    return (m >> keta) & 1;
}
int bget(ll m, int keta, int sinsuu) {
    m /= (ll) pow(sinsuu, keta);
    return m % sinsuu;
}
inline ll bit(int n) { return (1LL << (n)); }
inline ll bit(int n, int sinsuu) { return (ll) pow(sinsuu, n); }
//int bcou(ll m) { return __builtin_popcount(m & 0xFFFFFFFF) + __builtin_popcount(m >> 32); }
#define bcou __builtin_popcountll
//最下位ビット
int lbit(int n) {
    return n & -n;
}
//最上位ビット
int hbit(int n) {
    n |= (n >> 1);
    n |= (n >> 2);
    n |= (n >> 4);
    n |= (n >> 8);
    n |= (n >> 16);
    n |= (n >> 32);
    return n - (n >> 1);
}
//初期化は0を渡す
ll nextComb(ll &mask, int n, int r) {
    if (!mask)return mask = (1LL << r) - 1;
    ll x = mask & -mask; //最下位の1
    ll y = mask + x; //連続した下の1を繰り上がらせる
    ll res = ((mask & ~y) / x >> 1) | y;
    if (bget(res, n))return mask = 0;
    else return mask = res;
}

//n桁以下でビットがr個立っているもののvectorを返す
vl bitCombList(int n, int r) {
    vl res;
    int m = 0;
    while (nextComb(m, n, r)) {
        res.pb(m);
    }
    return res;
}
//大文字小文字を区別する
int altoiaZ(char c) {
    if ('A' <= c && c <= 'Z')return c - 'A';
    return c - 'a' + 26;
}
char itoalaZ(int i) {
    if (i < 26)return 'A' + i;
    return 'a' + i - 26;
}
//aもAも0を返す 基本小文字
int altoi(char c) {
    if ('A' <= c && c <= 'Z')return c - 'A';
    return c - 'a';
}
char itoal(int i) {
    return 'a' + i;
}
int ctoi(char c) { return c - '0'; }
char itoc(int i) { return i + '0'; }
int vtoi(vi &v) {
    int res = 0;
    if (sz(v) > 18) {
        debugline("vtoi");
        deb(sz(v));
        ole();
    }
    rep(i, sz(v)) {
        res *= 10;
        res += v[i];
    }
    return res;
}
vi itov(int i) {
    vi res;
    while (i) {
        res.pb(i % 10);
        i /= 10;
    }
    rev(res);
    return res;
}
vi stov(string &a) {
    int n = sz(a);
    vi ret(n);
    rep(i, n) {
        ret[i] = a[i] - '0';
    }
    return ret;
}
vector<vector<int>> ctoi(vector<vector<char>> s, char c) {
    int n = sz(s), m = sz(s[0]);
    vector<vector<int>> res(n, vector<int>(m));
    rep(i, n)rep(j, m)res[i][j] = s[i][j] == c;
    return res;
}
#define unique(v) v.erase( unique(v.begin(), v.end()), v.end() );

void compress(vi &a) {
    vi b;
    int len = a.size();
    for (int i = 0; i < len; ++i) {
        b.push_back(a[i]);
    }
    sort(b);
    unique(b);
    for (int i = 0; i < len; ++i) {
        a[i] = lower_bound(all(b), a[i]) - b.begin();
    }
}

void compress(int a[], int len) {
    vi b;
    for (int i = 0; i < len; ++i) {
        b.push_back(a[i]);
    }
    sort(b);
    unique(b);
    for (int i = 0; i < len; ++i) {
        a[i] = lower_bound(all(b), a[i]) - b.begin();
    }
}
//要素が見つからなかったときに困る
#define binarySearch(a, v) (binary_search(all(a),v))
#define lowerIndex(a, v) (lower_bound(all(a),v)-a.begin())
#define lowerBound(a, v) (*lower_bound(all(a),v))
#define upperIndex(a, v) (upper_bound(all(a),v)-a.begin())
#define upperBound(a, v) (*upper_bound(all(a),v))
#define ans(a) cout<<a<<endl;continue;
#define poll(a) q.front();q.pop()
#define dpoll(a) q.front();q.pop_front()
#define pollLast(a) q.back();q.pop_back()
#define pollBack(a) q.back();q.pop_back()
template<class T> inline void fin(T s) { cout << s << endl, exit(0); }

//便利 数学 math
int mod(int a, int m) { return (a % m + m) % m; }
int pow(int a) { return a * a; };
ll fact(int v) { return v <= 1 ? 1 : v * fact(v - 1); }
ll comi(int n, int r) {
    assert(n < 100);
    static vvi(pas, 100, 100);
    if (pas[0][0])return pas[n][r];
    pas[0][0] = 1;
    rep(i, 1, 100) {
        pas[i][0] = 1;
        rep(j, 1, i + 1)pas[i][j] = pas[i - 1][j - 1] + pas[i - 1][j];
    }
    return pas[n][r];
}
double comd(int n, int r) {
    assert(n < 2020);
    static vvd(comb, 2020, 2020);
    if (comb[0][0] == 0) {
        comb[0][0] = 1;
        rep(i, 2000) {
            comb[i + 1][0] = 1;
            rep(j, 1, i + 2) {
                comb[i + 1][j] = comb[i][j] + comb[i][j - 1];
            }
        }
    }
    return comb[n][r];
}
inline int gcd(int a, int b) {
    while (b) a %= b, swap(a, b);
    return a;
}
inline int gcd(vi b) {
    ll res = b[0];
    for (auto &&v :b)res = gcd(v, res);
    return res;
}
inline ll lcm(ll a, ll b) { return a / gcd(a, b) * b; }
inline ll ceil(ll a, ll b) {
    if (b == 0) {
        debugline("ceil");
        deb(a, b);
        ole();
        return -1;
    } else if (a < 0) {
        return 0;
    } else {
        return (a + b - 1) / b;
    }
}
//v * v >= aとなる最小のvを返す
ll sqrt(ll a) {
    if (a < 0) {
        debugline("sqrt");
        deb(a);
        ole();
    }
    ll res = (ll) std::sqrt(a);
    while (res * res < a)res++;
    return res;
}
double log(double e, double x) { return log(x) / log(e); }
ll sig(ll t) { return (1 + t) * t / 2; }
ll sig(ll s, ll t) { return (s + t) * (t - s + 1) / 2; }


namespace pnumber_tools {
#define Eratosthenes30_2  class Eratosthenes {public:virtual void initialize() {} virtual void generate(const int64_t x) = 0;        virtual void generate(const int64_t from, const int64_t to) = 0;        virtual int64_t count() = 0;        virtual bool prime(int x) = 0;        virtual int32_t version() = 0;        inline uint64_t PopCnt(uint64_t);        inline int BitToIndex(uint8_t);    };    uint64_t Eratosthenes::PopCnt(uint64_t bits) {        bits = (bits & 0x5555555555555555ULL) + ((bits >> 1) & 0x5555555555555555ULL);        bits = (bits & 0x3333333333333333ULL) + ((bits >> 2) & 0x3333333333333333ULL);        bits = (bits & 0x0f0f0f0f0f0f0f0fULL) + ((bits >> 4) & 0x0f0f0f0f0f0f0f0fULL);        return (bits * 0x0101010101010101ULL) >> (64 - 8);    }    int Eratosthenes::BitToIndex(uint8_t b) {        switch (b) {            case 1 << 0:                return 0;            case 1 << 1:                return 1;            case 1 << 2:                return 2;            case 1 << 3:                return 3;            case 1 << 4:                return 4;            case 1 << 5:                return 5;            case 1 << 6:                return 6;            case 1 << 7:                return 7;        }        return -1;    }    namespace {        const uint64_t kSegmentSize = 1000000;          const int32_t kMod30[] = {1, 7, 11, 13, 17, 19, 23, 29};        const int C1[] = {6, 4, 2, 4, 2, 4, 6, 2};        const int C0[][8] = {                {0, 0, 0, 0, 0, 0, 0, 1},                {1, 1, 1, 0, 1, 1, 1, 1},                {2, 2, 0, 2, 0, 2, 2, 1},                {3, 1, 1, 2, 1, 1, 3, 1},                {3, 3, 1, 2, 1, 3, 3, 1},                {4, 2, 2, 2, 2, 2, 4, 1},                {5, 3, 1, 4, 1, 3, 5, 1},                {6, 4, 2, 4, 2, 4, 6, 1},        };        const uint8_t kMask[][8] = {                {0xfe, 0xfd, 0xfb, 0xf7, 0xef, 0xdf, 0xbf, 0x7f},                {0xfd, 0xdf, 0xef, 0xfe, 0x7f, 0xf7, 0xfb, 0xbf},                {0xfb, 0xef, 0xfe, 0xbf, 0xfd, 0x7f, 0xf7, 0xdf},                {0xf7, 0xfe, 0xbf, 0xdf, 0xfb, 0xfd, 0x7f, 0xef},                {0xef, 0x7f, 0xfd, 0xfb, 0xdf, 0xbf, 0xfe, 0xf7},                {0xdf, 0xf7, 0x7f, 0xfd, 0xbf, 0xfe, 0xef, 0xfb},                {0xbf, 0xfb, 0xf7, 0x7f, 0xfe, 0xef, 0xdf, 0xfd},                {0x7f, 0xbf, 0xdf, 0xef, 0xf7, 0xfb, 0xfd, 0xfe},        };        const int64_t kSievedPrimes[] = {2, 3, 5, 7, 11, 13, 17, 19, 23};        const int64_t kInitialSize = 7 * 11 * 13 * 17 * 19 * 23;class Eratosthenes2 final : public Eratosthenes {    public:        void generate(const int64_t x) override;        void generate(const int64_t, const int64_t) override { assert(false); }        bool prime(int x) override;        int64_t count() override;        int32_t version() override { return 2; }        std::vector<uint8_t> flags_;    };    void Eratosthenes2::generate(const int64_t x) {        flags_.clear();        if (x > 10000000000)            return;        int64_t size = x / 30 + (x % 30 != 0);        flags_.resize(size, 0xff);        if (int r = x % 30) {            if (r <= 1) flags_[size - 1] = 0x0;            else if (r <= 7) flags_[size - 1] = 0x1;            else if (r <= 11) flags_[size - 1] = 0x3;            else if (r <= 13) flags_[size - 1] = 0x7;            else if (r <= 17) flags_[size - 1] = 0xf;            else if (r <= 19) flags_[size - 1] = 0x1f;            else if (r <= 23) flags_[size - 1] = 0x3f;            else if (r <= 29) flags_[size - 1] = 0x7f;        }        flags_[0] = 0xfe;        const uint64_t sqrt_x = std::ceil(std::sqrt(x) + 0.1);        const uint64_t sqrt_xi = sqrt_x / 30 + 1;        for (uint64_t i = 0; i < sqrt_xi; ++i) {            for (uint8_t flags = flags_[i]; flags; flags &= flags - 1) {                uint8_t lsb = flags & (-flags);                int ibit = BitToIndex(lsb);                const int32_t m = kMod30[ibit];                int32_t pm = 30 * i + 2 * m;                for (uint64_t j = i * pm + (m * m) / 30, k = ibit; j < flags_.size();                        j += i * C1[k] + C0[ibit][k], k = (k + 1) & 7) {                    flags_[j] &= kMask[ibit][k];                }            }        }    }int64_t Eratosthenes2::count() {            if (flags_.empty())                return -1;            int64_t ret = 3;            for (uint8_t f : flags_) {                ret += PopCnt(f);            }            return ret;        }        inline bool Eratosthenes2::prime(int x) {            static unordered_map<int, int> imap = {{1,  0},                                                   {7,  1},                                                   {11, 2},                                                   {13, 3},                                                   {17, 4},                                                   {19, 5},                                                   {23, 6},                                                   {29, 7}};            if (x == 2 || x == 3 || x == 5)return true;            else if (!(x & 1) || x % 3 == 0 || x % 5 == 0)return false;            return (flags_[x / 30] >> imap[x % 30]) & 1;        }}
    Eratosthenes30_2
    Eratosthenes2 era;
    int prime_len = 1e7;
    vector<int> primes;
    void setprime(int size = 1e7) {
        prime_len = size;
        era.generate(size);
        primes = {2, 3, 5};
        int nums[129] = {};
        nums[1] = 1;
        nums[2] = 7;
        nums[4] = 11;
        nums[8] = 13;
        nums[16] = 17;
        nums[32] = 19;
        nums[64] = 23;
        nums[128] = 29;
        //p = 30i + j
        int j = 0;
        int sflim = era.flags_.size();
        int i30 = 0;
        for (int i = 0; i < sflim; i++, i30 += 30) {
            uint8_t m = era.flags_[i];
            while (m) {
                j = m & -m;
                primes.push_back(i30 + nums[j]);
                m ^= j;
            }
        }
    }
    using u32 = unsigned int;//longlongになっていると思うが放置していいと思う
    using u64 = unsigned long long;
    using u128 = __uint128_t;

    //O(k log(n)^3)
    //32bit -> 270回程度
    //64bit -> 1260回程度
    //64bit以下で精度100%
    template<class BinOp> bool miller_rabin(const uint64_t &n, const uint64_t *witness, BinOp modmul) {
        if (n == 2) return true;
        if (n < 2 || n % 2 == 0) return false;
        const int64_t m = n - 1, d = m / (m & -m);
        auto modpow = [&](int64_t a, int64_t b) {
            int64_t res = 1;
            for (; b; b /= 2) {
                if (b & 1) res = modmul(res, a);
                a = modmul(a, a);
            }
            return res;
        };
        auto suspect = [&](uint64_t a, uint64_t t) {
            a = modpow(a, t);
            while (t != n - 1 && a != 1 && a != n - 1) {
                a = modmul(a, a);
                t = modmul(t, 2);
            }
            return a == n - 1 || t % 2 == 1;
        };
        for (const uint64_t *w = witness; *w; w++) {
            if (*w % n != 0 && !suspect(*w, d)) return false;
        }
        return true;
    }

//miller_rabin & エラトステネス
    bool prime(const int &n) {
        assert(n < 9223372036854775808ULL);
        if (!sz(primes))setprime();
        //エラトステネスの篩 偶数省略ver
        //[i] = i*2+1
        if (n < prime_len)return era.prime(n);
        if (n < 4294967296) {
            rep(i, 30) if (!(n % primes[i]))return false;
            constexpr uint64_t witness[] = {2, 7, 61, 0};
            auto modmul = [&](uint64_t a, uint64_t b) -> uint64_t { return a * b % n; };
            return miller_rabin(n, witness, modmul);
        } else {
            rep(i, 50) if (!(n % primes[i]))return false;
            constexpr uint64_t witness[] = {2, 325, 9375, 28178, 450775, 9780504, 1795265022, 0};
            auto modmul = [&](uint64_t a, uint64_t b) -> uint64_t { return (uint64_t) ((__uint128_t) a * b % n); };
            return miller_rabin(n, witness, modmul);
        }
    }
    vi divisors(int v) {
        vi res;
        double lim = std::sqrt(v);
        for (int i = 1; i <= lim; ++i) {
            if (v % i == 0) {
                res.pb(i);
                if (i != v / i)res.pb(v / i);
            }
        }
        return res;
    }
    vp factorization(int v) {
        assert(v);
        int tv = v;
        vp res;
        if (!sz(primes))setprime();
        for (auto &&p :primes) {
            if (v % p == 0)res.eb(p, 0);
            while (v % p == 0) {
                v /= p;
                res.back().second++;
            }
            if (v == 1 || p * p > tv)break;
        }
        if (v > 1)res.eb(v, 1);
        return res;
    }

    //注意 gcdを取る間隔 i%10 が場合によってはtleを引き起こす
    //うまくいかなかったら%1で動かすように変更した
    vp rho(int v) {
        assert(v);
        if (v == 1)return vp();
        vi ret;
        if (!sz(primes)) setprime();
        int lim = 300;                      //64bit
        if (v < 4294967296)lim = 80;      //32bit
        else if (v < 281474976710656LL)lim = 120; //48bit
        for (int i = 0; i < lim; i += 5) {
            //v<=1の判定回数を減らせる分高速化できる事を期待している
            while (v % primes[i] == 0) ret.pb(primes[i]), v /= primes[i];
            while (v % primes[i + 1] == 0) ret.pb(primes[i + 1]), v /= primes[i + 1];
            while (v % primes[i + 2] == 0) ret.pb(primes[i + 2]), v /= primes[i + 2];
            while (v % primes[i + 3] == 0) ret.pb(primes[i + 3]), v /= primes[i + 3];
            while (v % primes[i + 4] == 0) ret.pb(primes[i + 4]), v /= primes[i + 4];
            if (v <= 1) {
                sort(ret);//試し割りだけで終わった時
                return run_length(ret);
            }
        }
        int lower_prime_bound = primes[lim - 1] * primes[lim - 1];

        //試し割りするか
        mt19937 mr;
        mr.seed(clock());
        int c;
        auto fac = [&](int v, const int loop = 10) {
//        if (v == 4)return 2ll; //試し割りで消えた
            c = mr() % v;
            int x = mr() % v;
            int y = x;
            int d = 1;
            int lim = std::sqrt(v) + 2;
            //brentによる改良 差分を一操作で求められる
            for (int i = 1, mul = 1, next_2pow = 2; d == 1 && i <= lim; i++) {
                if (i == next_2pow) {
                    y = x;
                    next_2pow <<= 1;
                }
                x = ((u128) x * x + c) % v;
                mul = ((u128) mul * abs(x - y)) % v;//ユークリッドの互除法と同じ原理で因数は保たれる
                if (i % loop == 0) {//10とかなんとか iによっては死ぬ
                    d = gcd(mul, v);
                    mul = 1;
                }
            }
            if (d == v) return -1ll;
            return d;
        };

        queue<int> q;
        q.push(v);
        while (!q.empty()) {
            int v = q.front();
            q.pop();
            if (prime(v)) {
                ret.pb(v);
                continue;
            }
            int d = -1;
            //基本loop回数10でやり、駄目なら1で
            for (int i = 0; d == -1 && i < 5; i++)d = fac(v, 10);
            if (d == -1)while (d == -1)d = fac(v, 1);
            int d2 = v / d;
            rep(_, 2) {
                if (d <= lower_prime_bound)ret.pb(d);
                else q.push(d);
                d = d2;
            }
        }
        sort(ret);
        return run_length(ret);
    }
}
using namespace pnumber_tools;
//幾何 Pをcomplexとして扱う
template<class T, class U> bool eq(T a, U b) { return fabs(a - b) < eps; }
dou atan2(pd a) { return atan2(a.se, a.fi); }
dou angle(pd f, pd t) { return atan2(t.se - f.se, t.fi - f.fi); }
dou distance(pd a, pd b) { return hypot(a.fi - b.fi, a.se - b.se); }
//bを中心とするabcのtheta aからcにかけて時計回り
dou angle(pd a, pd b, pd c) {
    dou ax = a.fi - b.fi;
    dou ay = a.se - b.se;
    dou cx = c.fi - b.fi;
    dou cy = c.se - b.se;
    double ret = atan2(cy, cx) - atan2(ay, ax);
    if (ret < 0) ret += 2 * PI;
    return ret;
}

dou dot(pd a, pd b) { return a.fi * b.fi + a.se + b.se; }
dou cro(pd a, pd b) { return a.fi * b.se - a.se + b.fi; }

template<class T> struct edge {
    int f, t;
    T c;
    int id;
    int type;
    edge(int f, int t, T c = 1, int id = -1, int ty = -1) : f(f), t(t), c(c), id(id), type(ty) {}
    bool operator<(const edge &b) const { return c < b.c; }
    bool operator>(const edge &b) const { return c > b.c; }
};
template<class T> ostream &operator<<(ostream &os, edge<T> &e) {
    os << e.f << " " << e.t << " " << e.c;
    return os;
}
template<typename T> class graph {
protected:
    vector<bool> _used;
public :
    vector<vector<edge<T>>> g;
    vector<edge<T>> edges;
    int n;
    graph(int n) : n(n) { g.resize(n), _used.resize(n); }
    void clear() { g.clear(), edges.clear(); }
    void resize(int n) {
        this->n = n;
        g.resize(n);
        _used.resize(n);
    }
    int size() { return g.size(); }
    vector<edge<T> > &operator[](int i) { return g[i]; }
    virtual void add(int f, int t, T c, int id, int ty) = 0;
    virtual bool used(edge<T> &e) = 0;
    virtual bool used(int id) = 0;
    virtual void del(edge<T> &e) = 0;
    virtual void del(int id) = 0;
    virtual void set_edges() = 0;
};
template<typename T =ll> class digraph : public graph<T> {
public:
    using graph<T>::g;
    using graph<T>::n;
    using graph<T>::edges;
    using graph<T>::_used;
    int eid = 0;

    digraph(int n) : graph<T>(n) {}
    void add(int f, int t, T c = 1, int id = -1, int ty = -1) {
        if (!(0 <= f && f < n && 0 <= t && t < n)) {
            debugline("digraph add");
            deb(f, t, c, id, ty);
            ole();
        }
        if (id == -1)id = eid++;
        g[f].emplace_back(f, t, c, id, ty);
        edges.emplace_back(f, t, c, id, ty);
    }
    bool used(edge<T> &e) { return _used[e.id]; }
    bool used(int id) { return _used[id]; }
    void del(edge<T> &e) { _used[e.id] = _used[e.id ^ 1] = 1; }
    void del(int id) { _used[id] = _used[id ^ 1] = 1; }
    void set_edges() {
        if (sz(edges))return;
        rep(i, n)fora(e, g[i])edges.pb(e);
    }
};
template<class T=int> class undigraph : public graph<T> {
public:
    using graph<T>::g;
    using graph<T>::n;
    using graph<T>::edges;
    using graph<T>::_used;
    int eid = 0;
    undigraph(int n) : graph<T>(n) {
    }
    void add(int f, int t, T c = 1, int id = -1, int ty = -1) {
        if (!(0 <= f && f < n && 0 <= t && t < n)) {
            debugline("undigraph add");
            deb(f, t, c, id, ty);
            ole();
        }
        if (id == -1)id = eid, eid += 2;
        g[f].emplace_back(f, t, c, id, ty);
        g[t].emplace_back(t, f, c, id + 1, ty);
        edges.emplace_back(f, t, c, id, ty);
        edges.emplace_back(t, f, c, id + 1, ty);
    }
    void add(edge<T> &e) {
        int f = e.f, t = e.t, ty = e.type;
        T c = e.c;
        add(f, t, c, ty);
    }
    bool used(edge<T> &e) { return _used[e.id]; }
    bool used(int id) { return _used[id]; }
    void del(edge<T> &e) { _used[e.id] = _used[e.id ^ 1] = 1; }
    void del(int id) { _used[id] = _used[id ^ 1] = 1; }
    void set_edges() {
        if (sz(edges))return;
        rep(i, n)fora(e, g[i])edges.pb(e);
    }
};
template<typename T> struct radixheap {
    vector<pair<u64, T> > v[65];
    u64 size, last;
    radixheap() : size(0), last(0) {}
    bool empty() const { return size == 0; }
    inline int getbit(int a) { return a ? 64 - __builtin_clzll(a) : 0; }
    void push(u64 key, const T &value) {
        ++size;
        v[getbit(key ^ last)].emplace_back(key, value);
    }
    pair<u64, T> pop() {
        if (v[0].empty()) {
            int idx = 1;
            while (v[idx].empty()) ++idx;
            last = min_element(begin(v[idx]), end(v[idx]))->first;
            for (auto &p : v[idx]) v[getbit(p.first ^ last)].emplace_back(p);
            v[idx].clear();
        }
        --size;
        auto ret = v[0].back();
        v[0].pop_back();
        return ret;
    }
};
vi dijkstra2(const graph<int> &g, int s, int cant_arrive = -1) {
    if (!(0 <= s && s < g.n)) {
        debugline("dijkstra");
        deb(s, g.n);
        ole();
    }
    int initValue = MAX(int);
    vi dis(g.n, initValue);
    radixheap<int> q;
    dis[s] = 0;
    q.push(0, s);
    while (!q.empty()) {
        int nowc, i;
        tie(nowc, i) = q.pop();
        if (dis[i] != nowc)continue;
        for (auto &&e  : g.g[i]) {
            int to = e.t;
            int c = nowc + e.c;
            if (dis[to] > c) {
                dis[to] = c;
                q.push(dis[to], to);
            }
        }
    }
    //基本、たどり着かないなら-1
    if (cant_arrive == -1)for (auto &&d :dis) if (d == initValue)d = -1;
    return dis;
}

template<class T> vector<T> dijkstra(const graph<T> &g, int s, int cant_arrive = -1) {
    if (!(0 <= s && s < g.n)) {
        debugline("dijkstra");
        deb(s, g.n);
        ole();
    }
    T initValue = MAX(T);
    vector<T> dis(g.n, initValue);
    priority_queue<pair<T, int>, vector<pair<T, int>>, greater<pair<T, int>>> q;
    dis[s] = 0;
    q.emplace(0, s);
    while (q.size()) {
        T nowc = q.top().fi;
        int i = q.top().se;
        q.pop();
        if (dis[i] != nowc)continue;
        for (auto &&e  : g.g[i]) {
            int to = e.t;
            T c = nowc + e.c;
            if (dis[to] > c) {
                dis[to] = c;
                q.emplace(dis[to], to);
            }
        }
    }
    //基本、たどり着かないなら-1
    if (cant_arrive == -1)for (auto &&d :dis) if (d == initValue)d = -1;
    return dis;
}
template<class T> vector<vector<T>> warshall(const graph<T> &g, int cant_arrive = -1) {
    int n = g.n;
    vector<vector<T> > dis(n, vector<T>(n, linf));
    rep(i, n)fora(e, g.g[i])chmi(dis[e.f][e.t], e.c);
    rep(i, n)dis[i][i] = 0;
    rep(k, n)rep(i, n)rep(j, n)chmi(dis[i][j], dis[i][k] + dis[k][j]);
    //基本、たどり着かないなら-1
    if (cant_arrive == -1)rep(i, n)rep(j, n) if (dis[i][j] == linf)dis[i][j] = -1;
    return dis;
}

template<class T=int> class tree : public undigraph<T> {
public:
    using undigraph<T>::g;
    using undigraph<T>::n;
    using undigraph<T>::edges;
    using undigraph<T>::_used;
    vi disv;
private:
    bool never = 1;
    int root = -1;
    vi dep;//到着した順の深さ
    vi vis;//到着した順
    vi first;//初めてiに到着した時
    vector<int> bestt;//タイミングを持つ
    int sn = 0;//seg木の底辺の長さ euler tourの長さ
    void built() {
        never = 0;
        n = g.size();
        //euler tour
        disv.resize(n);
        first.resize(n);
        dep.resize(n * 2);
        vis.resize(n * 2);
        function<void(int, int, int, int &)> dfs = [&](int i, int p, int d, int &k) {
            first[i] = k;
            vis[k] = i;
            dep[k++] = d;
            forg(gi, g[i]) {
                if (t == p)continue;
                disv[t] = disv[i] + c;
                dfs(t, i, d + 1, k);
                vis[k] = i;
                dep[k++] = d;
            }
        };
        int k = 0;
        dfs(root, -1, 0, k);
        //rmq
        sn = 1;
        while (sn < n * 2)sn *= 2;

        bestt.resize(2 * sn - 1);
        rep(i, k)bestt[i + sn - 1] = i;//時間を持つ
        rer(i, sn - 2) {
            int lt = i * 2 + 1, rt = i * 2 + 2;
            if (dep[bestt[i * 2 + 1]] < dep[bestt[i * 2 + 2]]) {
                bestt[i] = bestt[i * 2 + 1];
            } else {
                bestt[i] = bestt[i * 2 + 2];
            }
        }
    }
    //最小の深さを持つタイミングが知りたい
    inline int lca_rmq(int l, int r) {
        int midv = inf;
        int midi = -1;
        l += sn - 1;
        r += sn - 1;
        while (l < r) {
            //右なら、次に右上へ移動してしまうので
            if (!(l & 1)) {
                if (chmi(midv, dep[bestt[l]])) {
                    midi = bestt[l];
                }
            }
            //右なら、左を取らないと機会を逃す
            if (!(r & 1)) {
                if (chmi(midv, dep[bestt[r - 1]])) {
                    midi = bestt[r - 1];
                }
            }
            l >>= 1;
            r = (r - 1) >> 1;
        }
        return vis[midi];
    }

public:
    tree(int n, int root = 0) : undigraph<T>(n), root(root) {}
    bool leaf(int v) {
        return sz(g[v]) == 1 && v != root;
    }
    int lca(int u, int v) {
        if (never)built();
        if (first[u] > first[v])swap(u, v);
        return lca_rmq(first[u], first[v] + 1);
    }
    int dis(int u, int v) {
        if (never) built();
        if (first[u] > first[v])swap(u, v);
        int p = lca_rmq(first[u], first[v] + 1);
        return disv[u] + disv[v] - disv[p] * 2;
    }

};
//辺によりメモリを大量消費
// よってedgesを消している
//頂点10^6でメモリを190MB(制限の8割)使う

//軽量化のため、辺を持たないbig gridクラスがあってもいいかもしれない
//
template<class T=int> class grid_k6 : public undigraph<T> {
public:
    using undigraph<T>::g;
    using undigraph<T>::n;
    using undigraph<T>::edges;
    using undigraph<T>::_used;
    int H, W;
    int eid = 0;
    void add(int f, int t, T c = 1, int id = -1, int ty = -1) {
        if (!(0 <= f && f < n && 0 <= t && t < n)) {
            debugline("grid_k6 add");
            deb(f, t, c, id, ty);
            ole();
        }
        g[f].emplace_back(f, t, c, eid++, ty);
        g[t].emplace_back(t, f, c, eid++, ty);
    }
    int getid(int h, int w) {
        if (!inside(h, w, H, W))return -1;
        return W * h + w;
    }
    P get2(int id) {
        return mp(id / W, id % W);
    }
    P operator()(int id) {
        return get2(id);
    }
    int operator()(int h, int w) {
        return getid(h, w);
    }
    grid_k6(int H, int W) : H(H), W(W), undigraph<T>(H * W) {
        rep(h, H) {
            rep(w, W) {
                int f = getid(h, w);
                if (w + 1 < W) add(f, getid(h, w + 1));
                if (h + 1 < H)add(f, getid(h + 1, w));
            }
        }
    }
    grid_k6(_vvc ba, char wall = '#') : H(sz(ba)), W(sz(ba[0])), undigraph<T>(sz(ba) * sz(ba[0])) {
        rep(h, H) {
            rep(w, W) {
                if (ba[h][w] == wall)con;
                int f = getid(h, w);
                if (w + 1 < W && ba[h][w + 1] != wall) {
                    add(f, getid(h, w + 1));
                }
                if (h + 1 < H && ba[h + 1][w] != wall) {
                    add(f, getid(h + 1, w));
                }
            }
        }
    }
    void add(int fh, int fw, int th, int tw) {
        add(getid(fh, fw), getid(th, tw));
    }
    void set_edges() {
        rep(i, n)fora(e, g[i])edges.pb(e);
    }
};
//左上から右下に移動できる
template<class T=int> class digrid_k6 : public digraph<T> {
public:
    using digraph<T>::g;
    using digraph<T>::n;
    using digraph<T>::edges;
    using digraph<T>::_used;
    int H, W;
    int eid = 0;
    void add(int f, int t, T c = 1, int id = -1, int ty = -1) {
        if (!(0 <= f && f < n && 0 <= t && t < n)) {
            debugline("digrid_k6 add");
            deb(f, t, c, id, ty);
            ole();
        }
        g[f].emplace_back(f, t, c, eid++, ty);
    }
    int getid(int h, int w) {
        if (!inside(h, w, H, W))return -1;
        return W * h + w;
    }
    P get2(int id) {
        return mp(id / W, id % W);
    }
    P operator()(int id) {
        return get2(id);
    }
    int operator()(int h, int w) {
        return getid(h, w);
    }
    digrid_k6(int H, int W) : H(H), W(W), digraph<T>(H * W) {
        rep(h, H) {
            rep(w, W) {
                int f = getid(h, w);
                if (w + 1 < W) add(f, getid(h, w + 1));
                if (h + 1 < H)add(f, getid(h + 1, w));
            }
        }
    }
    digrid_k6(_vvc ba, char wall = '#') : H(sz(ba)), W(sz(ba[0])), digraph<T>(sz(ba) * sz(ba[0])) {
        rep(h, H) {
            rep(w, W) {
                if (ba[h][w] == wall)con;
                int f = getid(h, w);
                if (w + 1 < W && ba[h][w + 1] != wall) {
                    add(f, getid(h, w + 1));
                }
                if (h + 1 < H && ba[h + 1][w] != wall) {
                    add(f, getid(h + 1, w));
                }
            }
        }
    }
    void add(int fh, int fw, int th, int tw) {
        add(getid(fh, fw), getid(th, tw));
    }
    void set_edges() {
        rep(i, n)fora(e, g[i])edges.pb(e);
    }
};
template<class T> bool nibu(const graph<T> &g) {
    int size = 0;
    rep(i, g.n)size += sz(g.g[i]);
    if (size == 0)return true;

    UnionFind uf(g.n * 2);
    rep(i, g.n)fora(e, g.g[i])uf.unite(e.f, e.t + g.n), uf.unite(e.f + g.n, e.t);
    rep(i, g.n)if (uf.same(i, i + g.n))return 0;
    return 1;
}
//二部グラフを色分けした際の頂点数を返す
template<class T> vp nibug(graph<T> &g) {
    vp cg;
    if (!nibu(g)) {
        debugline("nibu");
        ole();
    }
    int _n = g.size();
    vb _was(_n);
    queue<P> q;
    rep(i, _n) {
        if (_was[i])continue;
        q.push(mp(i, 1));
        _was[i] = 1;
        int red = 0;
        int coun = 0;
        while (q.size()) {
            int now = q.front().fi;
            int col = q.front().se;
            red += col;
            coun++;
            q.pop();
            forg(gi, g[now]) {
                if (_was[t])continue;
                q.push(mp(t, col ^ 1));
                _was[t] = 1;
            }
        }
        cg.push_back(mp(red, coun - red));
    }
    return cg;

}
template<class T> ostream &operator<<(ostream &os, graph<T> &g) {
    rep(i, sz(g)) {
        forg(gi, g[i]) {
            os << f << " " << t << " " << c << endl;
        }
    }
    return os;
}

//機能拡張
vp vtop(vi &a, vi &b) {
    vp res(sz(a));
    rep(i, sz(a))res[i] = mp(a[i], b[i]);
    return res;
}
void ptov(vp &p, vi &a, vi &b) {
    a.resize(sz(p));
    b.resize(sz(p));
    rep(i, sz(p))a[i] = p[i].fi, b[i] = p[i].se;
}
template<typename _CharT, typename _Traits, typename _Alloc>
basic_string<_CharT, _Traits, _Alloc>
operator+(const basic_string<_CharT, _Traits, _Alloc> &__lhs, const int __rv) {
    basic_string<_CharT, _Traits, _Alloc> __str(__lhs);
    __str.append(to_string(__rv));
    return __str;
}
template<typename _CharT, typename _Traits, typename _Alloc>
void operator+=(basic_string<_CharT, _Traits, _Alloc> &__lhs, const int __rv) {
    __lhs += to_string(__rv);
}
template<typename _CharT, typename _Traits, typename _Alloc>
basic_string<_CharT, _Traits, _Alloc>
operator+(const basic_string<_CharT, _Traits, _Alloc> &__lhs, const signed __rv) {
    basic_string<_CharT, _Traits, _Alloc> __str(__lhs);
    __str.append(to_string(__rv));
    return __str;
}
template<typename _CharT, typename _Traits, typename _Alloc>
void operator+=(basic_string<_CharT, _Traits, _Alloc> &__lhs, const signed __rv) {
    __lhs += to_string(__rv);
}
template<class T, class U> void operator+=(queue<T> &a, U v) {
    a.push(v);
}
template<class T, class U> void operator+=(deque<T> &a, U v) {
    a.push_back(v);
}
template<class T, class U>
priority_queue<T, vector<T>, greater<T> > &operator+=(priority_queue<T, vector<T>, greater<T> > &a, U v) {
    a.push(v);
    return a;
}
template<class T, class U> priority_queue<T> &operator+=(priority_queue<T> &a, U v) {
    a.push(v);
    return a;
}
template<class T, class U> set<T> &operator+=(set<T> &a, U v) {
    a.insert(v);
    return a;
}
template<class T, class U> set<T, greater<T>> &operator+=(set<T, greater<T>> &a, U v) {
    a.insert(v);
    return a;
}
template<class T, class U> vector<T> &operator+=(vector<T> &a, U v) {
    a.pb(v);
    return a;
}
template<class T, class U> vector<T> operator+(const vector <T> &a, U v) {
    vector<T> ret = a;
    ret += v;
    return ret;
}
template<class T, class U> vector<T> operator+(U v, const vector <T> &a) {
    vector<T> ret = a;
    ret.insert(ret.begin(), v);
    return ret;
}
template<class T> vector<T> operator+(vector<T> &a, vector <T> &b) {
    vector<T> ret;
    ret = a;
    fora(v, b)ret += v;
    return ret;
}
template<class T> vector<T> &operator+=(vector<T> &a, vector <T> &b) {
    fora(v, b)a += v;
    return a;
}
template<class T, class U> vector<T> &operator+=(vector<T> &a, initializer_list<U> v) {
    for (auto &&va :v)a.pb(va);
    return a;
}
template<class T> vector<T> &operator-=(vector<T> &a, vector <T> &b) {
    if (sz(a) != sz(b)) {
        debugline("vector<T> operator-=");
        deb(a);
        deb(b);
        ole();
    }
    rep(i, sz(a))a[i] -= b[i];
    return a;
}
template<class T> vector<T> operator-(vector<T> &a, vector <T> &b) {
    if (sz(a) != sz(b)) {
        debugline("vector<T> operator-");
        deb(a);
        deb(b);
        ole();
    }
    vector<T> res(sz(a));
    rep(i, sz(a))res[i] = a[i] - b[i];
    return res;
}


template<typename T> void remove(vector<T> &v, unsigned int i) { v.erase(v.begin() + i); }
template<typename T> void remove(vector<T> &v, unsigned int s, unsigned int e) {
    v.erase(v.begin() + s, v.begin() + e);
}
template<typename T> void removen(vector<T> &v, unsigned int s, unsigned int n) {
    v.erase(v.begin() + s, v.begin() + s + n);
}
template<typename T> void erase(vector<T> &v, unsigned int i) { v.erase(v.begin() + i); }
template<typename T> void erase(vector<T> &v, unsigned int s, unsigned int e) {
    v.erase(v.begin() + s, v.begin() + e);
}
template<typename T> void erasen(vector<T> &v, unsigned int s, unsigned int n) {
    v.erase(v.begin() + s, v.begin() + s + n);
}
template<typename T, typename U> void insert(vector<T> &v, unsigned int i, U t) { v.insert(v.begin() + i, t); }
template<typename T, typename U> void push_front(vector<T> &v, U t) { v.insert(v.begin(), t); }
template<typename T, typename U> void insert(vector<T> &v, unsigned int i, vector<T> list) {
    for (auto &&va :list)v.insert(v.begin() + i++, va);
}
template<typename T, typename U> void insert(vector<T> &v, initializer_list<U> list) {
    for (auto &&va :list)v.pb(va);
}
template<typename T, typename U> void insert(vector<T> &v, unsigned int i, initializer_list<U> list) {
    for (auto &&va :list)v.insert(v.begin() + i++, va);
}
template<typename T> void insert(set<T> &v, vector<T> list) {
    for (auto &&va :list)v.insert(va);
}
template<typename T> void insert(set<T> &v, initializer_list<T> list) {
    for (auto &&va :list)v.insert(va);
}


//閉路がなければtrue
bool topo(vi &res, digraph<int> &g) {
    int n = g.g.size();
    vi nyu(n);
    rep(i, n)for (auto &&e :g[i])nyu[e.t]++;
    queue<int> st;
    rep(i, n)if (nyu[i] == 0)st.push(i);
    while (st.size()) {
        int v = st.front();
        st.pop();
        res.pb(v);
        fora(e, g[v]) if (--nyu[e.t] == 0)st.push(e.t);
    }
    return res.size() == n;
}
//辞書順最小トポロジカルソート
bool topos(vi &res, digraph<int> &g) {
    int n = g.g.size();
    vi nyu(n);
    rep(i, n)for (auto &&e :g[i])nyu[e.t]++;
    //小さい順
    priority_queue<int, vector<int>, greater<int> > q;
    rep(i, n)if (nyu[i] == 0)q.push(i);
    while (q.size()) {
        int i = q.top();
        q.pop();
        res.pb(i);
        fora(e, g[i])if (--nyu[e.t] == 0)q.push(e.t);
    }
    return res.size() == n;
}
vector<string> split(const string a, const char deli) {
    string b = a + deli;
    int l = 0, r = 0, n = b.size();
    vector<string> res;
    rep(i, n) {
        if (b[i] == deli) {
            r = i;
            if (l < r)res.push_back(b.substr(l, r - l));
            l = i + 1;
        }
    }
    return res;
}
vector<string> split(const string a, const string deli) {
    string b = a + deli;
    int l = 0, r = 0, n = b.size(), dn = deli.size();
    vector<string> res;
    rep(i, n) {
        if (i + dn <= n && b.substr(i, i + dn) == deli) {
            r = i;
            if (l < r)res.push_back(b.substr(l, r - l));
            i += dn - 1;
            l = i + 1;
        }
    }
    return res;
}
void yn(bool a) {
    if (a)cout << "yes" << endl;
    else cout << "no" << endl;
}
void Yn(bool a) {
    if (a)cout << "Yes" << endl;
    else cout << "No" << endl;
}
void YN(bool a) {
    if (a)cout << "YES" << endl;
    else cout << "NO" << endl;
}
void fyn(bool a) {
    if (a)cout << "yes" << endl;
    else cout << "no" << endl;
    exit(0);
}
void fYn(bool a) {
    if (a)cout << "Yes" << endl;
    else cout << "No" << endl;
    exit(0);
}
void fYN(bool a) {
    if (a)cout << "YES" << endl;
    else cout << "NO" << endl;
    exit(0);
}
void Possible(bool a) {
    if (a)cout << "Possible" << endl;
    else cout << "Impossible" << endl;
    exit(0);
}

int n, m, k, d, H, W, x, y, z, q;
int cou;
vi a, b, c;
//vvi (s, 0, 0);
vvc (ba, 0, 0);
vp p;
str s;
mint dp[303][k5][2][2];
void solve() {
    cin >> n;
    na(a, n);
    int su = sum(a);
    dp[0][0][0][0] = 1;
    vi ss, t;
    vi usd(k5);
    ss += 0;
    rep(i, n) {
        fora(s, ss) {
            rep(g, 2) {
                rep(b, 2) {
                    if (dp[i][s][g][b] == 0)con;
                    int ns = s + a[i];
                    if (usd[s] == 0)usd[s] = 1, t += s;
                    if (usd[ns] == 0)usd[ns] = 1, t += ns;
                    //取る
                    dp[i + 1][ns][g][b] += dp[i][s][g][b];
                    //g
                    dp[i + 1][s][1][b] += dp[i][s][g][b];
                    dp[i + 1][s][g][1] += dp[i][s][g][b];
                }
            }
        }
        fill(usd,0);
        swap(ss, t);
        t.clear();
    }
    mint res = mpow(3, n);
    rep(s, k5) {
        if (s >= su / 2.) {
            res -= sum(dp[n][s]) * 3;
        }
    }
    if (ev(su)) {
        res += dp[n][su / 2][0][1] * 3;
    }
    cout << res << endl;
}

int my(int n, vi &a) {
    return 0;
}
int sister(int n, vi &a) {
    int ret = 0;

    return ret;
}

signed main() {
    solve();

#define _arg n,a
#ifdef _DEBUG
    bool bad = 0;
    for (int i = 0, ok = 1; i < k5 && ok; i++) {
        int n = rand(1, 8);
        vi a = ranv(n, 1, 10);
        int myres = my(_arg);
        int res = sister(_arg);
        ok = myres == res;
        if (!ok) {
            outl(_arg);
            cerr << "正解 : " << res << endl;
            cerr << "自分  : " << myres << endl;
            bad = 1;
            break;
        }
    }
    if (!bad) {
//        cout << "完璧 : solveを書き直そう" << endl;
//        cout << "     : そして、solve()を呼び出すのだ" << endl;
//        cout << "     : cin>>n; na(a,n);も忘れるな" << endl;
    }
#endif
    return 0;
};

