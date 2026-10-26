#include <bits/stdc++.h>
using namespace std;

typedef long long ll;
typedef vector<int> vi;
typedef pair<ll, int> pii;

#define pb push_back
#define eb emplace_back
#define mp make_pair
#define fi first
#define se second
#define rep(i,n) rep2(i,0,n)
#define rep2(i,m,n) for(int i=m;i<(n);i++)
#define ALL(c) (c).begin(),(c).end()
#define dump(x) cout << #x << " = " << (x) << endl
constexpr ll TEN(int n) { return (n == 0) ? 1 : 10 * TEN(n-1); }

template<class T, class U>
ostream& operator<<(ostream& os, const pair<T, U>& p) {
	os<<"("<<p.first<<","<<p.second<<")";
	return os;
}

template<class T>
ostream& operator<<(ostream& os, const vector<T>& v) {
	os<<"{";
	rep(i, v.size()) {
		if (i) os<<",";
		os<<v[i];
	}
	os<<"}";
	return os;
}

#define MN 3010
#define INF (1LL<<60)

//!!!!!!overflow
struct edge {
	int to, cap, rev;
	ll cost;
	edge(int to, int cap, ll cost, int rev) : to(to), cap(cap), cost(cost), rev(rev){}
};

int N;
vector<edge> g[MN];
ll h[MN], dst[MN];
int prevv[MN], preve[MN];

inline void add_edge(int f, int t, int cap, ll cost) {
	g[f].emplace_back(t, cap, cost, (int)g[t].size());
	g[t].emplace_back(f, 0, -cost, (int)g[f].size() - 1);
}

ll min_cost_flow(int s, int t, int f) {
	ll res = 0;
	memset(h, 0, sizeof(h));

	while (f > 0) {
		priority_queue<pii, vector<pii>, greater<pii> > que;
		fill(dst, dst + N + 1, INF);
		dst[s] = 0;
		que.push(mp(0, s));
		while (!que.empty()) {
			pii p = que.top(); que.pop();
			int v = p.se;
			if (dst[v] < p.fi) continue;
			rep(i, g[v].size()) {
				edge &e = g[v][i];
				ll nd = dst[v] + e.cost + h[v] - h[e.to];
				if (e.cap > 0 && dst[e.to] > nd) {
					dst[e.to] = nd;
					prevv[e.to] = v;
					preve[e.to] = i;
					que.push(mp(dst[e.to], e.to));
				}
			}
		}

		if (dst[t] == INF) return -1;
		rep(i, N) h[i] += dst[i];

		int d = f;
		for (int v = t; v != s; v = prevv[v]) {
			d = min(d, g[prevv[v]][preve[v]].cap);
		}
		f -= d;
		res += d * h[t];
		for (int v = t; v != s; v = prevv[v]) {
			edge &e = g[prevv[v]][preve[v]];
			e.cap -= d;
			g[v][e.rev].cap += d;
		}
	}

	return res;
}

const int tx[4] = {1, 1, -1, -1};
const int ty[4] = {1, -1, 1, -1};

int main() {
	int n; cin >> n;

	int num = 0;
	int s = 0;

	rep(i, n) {
		int x, y, c; cin >> x >> y >> c;
		add_edge(s, i + 1, c, 0ll);
		rep(d, 4) {
			add_edge(i + 1, n + 1 + d, 1e9, x * tx[d] + y * ty[d]);
		}

		num += c;
	}

	int t = 1 + n + 4 + n;
	N = n * 2 + 6;

	rep(i, n) {
		int x, y, c; cin >> x >> y >> c;
		add_edge(1 + n + 4 + i, t, c, 0ll);
		rep(d, 4) {
			add_edge(n + 1 + d, 1 + n + 4 + i, 1e9, x * tx[3-d] + y * ty[3-d]);
		}		
	}

	ll ans = min_cost_flow(s, t, num);
	cout << -ans << endl;

	return 0;
}