#include <cstdio>
#include <iostream>
#include <string>
#include <sstream>
#include <stack>
#include <algorithm>
#include <cmath>
#include <queue>
#include <map>
#include <set>
#include <cstdlib>
#include <bitset>
#include <tuple>
#include <assert.h>
#include <deque>
#include <bitset>
#include <iomanip>
#include <limits>
#include <chrono>
#include <random>
#include <array>
#include <unordered_map>
#include <functional>
#include <complex>
#include <numeric>
template<class T> inline bool chmax(T& a, T b) { if (a < b) { a = b; return 1; } return 0; }
template<class T> inline bool chmin(T& a, T b) { if (a > b) { a = b; return 1; } return 0; }

constexpr long long MAX = 5100000;
constexpr long long INF = 1LL << 60;
constexpr int inf = 1 << 28;
//constexpr long long mod = 1000000007LL;
//constexpr long long mod = 998244353LL;

using namespace std;
typedef unsigned long long ull;
typedef long long ll;

void solve() {
	int n, m; scanf("%d %d", &n, &m); if (n == 0 && m == 0) exit(0);
	vector<vector<int>> g(n);
	for (int i = 0; i < m; i++) {
		int u, v; scanf("%d %d", &u, &v);
		u--; v--;
		g[u].push_back(v);
		g[v].push_back(u);
	}
	vector<int> col(n, -1);
	col[0] = 0;
	queue<int> q; q.push(0);
	int bl = 0, wh = 0;
	while (!q.empty()) {
		int cur = q.front();
		q.pop();
		if (col[cur]) bl++;
		else wh++;
		for (auto next : g[cur]) {
			if (col[next] == -1) {
				col[next] = (col[cur] ^ 1);
				q.push(next);
			}
			else {
				if (col[next] == col[cur]) {
					puts("0");
					return;
				}
			}
		}
	}
	vector<int> res;
	if (bl % 2 == 0) res.push_back(bl / 2);
	if (wh % 2 == 0) res.push_back(wh / 2);
	sort(res.begin(), res.end());
	res.erase(unique(res.begin(), res.end()), res.end());
	cout << res.size() << "\n";
	for (int i = 0; i < res.size(); i++) {
		cout << res[i] << "\n";
	}
}
int main()
{
	/*
	cin.tie(nullptr);
	ios::sync_with_stdio(false);
	*/
	int kkt = 89;
	while (kkt) {
		solve();
	}
	return 0;

}

