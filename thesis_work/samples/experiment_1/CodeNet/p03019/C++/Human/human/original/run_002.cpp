#include <bits/stdc++.h>
using namespace std;

#define TRACE(x)
#define WATCH(x) TRACE(cout << #x" = " << x << endl)
#define WATCHR(a, b) TRACE(for (auto it=a; it!=b;) cout << *(it++) << " "; cout << endl)
#define WATCHC(V) TRACE({cout << #V" = "; WATCHR(V.begin(), V.end());})

#define all(x) (x).begin(), (x).end()

using ll = long long;
using vi = vector<int>;
using vvi = vector<vi>;
using vll = vector<ll>;
using vvll = vector<vll>;
using vb = vector<bool>;
using vs = vector<string>;

mt19937 rng(chrono::steady_clock::now().time_since_epoch().count());

template<typename T> struct binary_indexed_tree {
    int S;
    vector<T> table;

    binary_indexed_tree<T>(int _S = 0) : S(_S) {
        table.resize(S+1);
    }

    // Adds v to the element at index i
    void add(int i, T v) {
        for (i++; i <= S; i += i&-i)
            table[i] = table[i] + v;
    }

    // Returns the sum of the elements at indices [0, i)
    T sum(int i) const {
        T res = T();
        for (; i; i -= i&-i)
            res = res + table[i];
        return res;
    }

    // Returns the sum of the elements at indices [l, r)
    T sum(int l, int r) const {
        return sum(r) - sum(l);
    }

    /*
     * Returns in O(logS) the first i in [0, S] such that comp(sum(i)) is true.
     * Returns -1 if no such i exists.
     * Requires that comp(sum(i)) is non-decreasing in i.
     * The empty prefix is considered to have sum equal to T's default value.
     */
    int lower_bound(const auto& comp) const {
        int inx = 0;
        T cur = T();
        if (comp(cur)) return 0;

        for (int i = 31 - __builtin_clz(S); i >= 0; i--) {
            int nxt = inx + (1 << i);
            if (nxt <= S && !comp(cur + table[nxt])) {
                inx = nxt;
                cur = cur + table[nxt];
            }
        }
        return inx < S ? inx + 1 : -1;
    }
};

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(0), cout.tie(0);

    int N, X;
    cin >> N >> X;

    ll __cur = 0;
    vector<array<ll, 4>> exams(N);

    for (int i = 0, b, l, r; i < N; i++) {
        cin >> b >> l >> r;
        __cur += ll(b) * ll(l);
        exams[i] = { ll(l) * b + ll(r) * (X - b), b, l, r };
    }
    const ll cur = __cur;

    sort(all(exams));
    reverse(all(exams));

    binary_indexed_tree<ll> full(N + 1);
    for (int i = 0; i < N; i++) {
        full.add(i, exams[i][0]);
    }

    ll ans = cur ? (full.lower_bound([&](ll tot) { return tot >= cur; })) * ll(X) : 0;
    assert(ans <= ll(N) * ll(X));

    for (int part = 0; part < N; part++) {
        const auto ex = exams[part];
        const ll mv = ex[0], b = ex[1], l = ex[2], r = ex[3];
        full.add(part, -mv);

        int use_rest = 0;
        ll got = 0;
        if (mv < cur) {
            ll need = cur - mv;
            int rest = full.lower_bound([&](ll tot) { return tot >= need; });
            got = full.sum(rest);
            assert(got >= need);
            assert(rest != part + 1);
            use_rest = rest > part ? rest - 1 : rest;
        }

        if (got < cur && got + mv > cur) {
            ll cand = use_rest * ll(X);
            ll need = cur - got;

            ll take = min((need + l - 1) / l, b);
            if (take * l < need) {
                ll more = (need - take * l + r - 1) / r;
                assert(more <= X - b);
                assert(need - take * l - more * r <= 0);
                take += more;
                assert(take <= X);
            } else assert(need - take * l <= 0);

            TRACE(cout << "can use exam with fv " << mv << " taking " << take << " and " << cand << " from others to achieve " << cand + take << endl;)

            ans = min(ans, cand + take);
        }

        full.add(part, mv);
    }

    cout << ans << endl;

    return 0;
}

