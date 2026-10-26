#include <bits/stdc++.h>
 
#define forn(i, n) for (llong i = 0ll; i < (llong) n; ++i)
#define fornn(i, l, r) for (llong i = (llong) l; i < (llong) r; ++i)
#define size(x) ((int) (x.size()))
 
using namespace std;
 
typedef long long llong;
const llong inf = (llong) 1e+9 + 7ll;
const llong linf = (llong) 1e+18 + 7ll;
const long double eps = (long double) 1e-9;
const long double pi = acosl((long double) -1.0);
const int alph = 26;
 
mt19937 mrand(random_device{} ()); 
 
template<typename T, typename U> inline llong umin(const T& a, const U& b) { return a < b ? a : b; }
template<typename T, typename U> inline llong umax(const T& a, const U& b) { return a > b ? a : b; }
 
static char buff[(int) 2e6 + 17]; // reads std::string
const int maxn = (int) 3e6 + 17;
 
struct pt
{
	int x, y;
	pt() { }
};
 
struct fenwick
{
	int n;
	unordered_map<int, int> F;
 
	fenwick()
	{
		n = inf;
		F.rehash(200000 * 17);
		//F.max_load_factor();
	}
 
	void upd(int i, int x)
	{
		for (; i < n; i |= i + 1)
			F[i] = max(F[i], x);
	}
 
	int get(int i)
	{
		int res = 0;
 
		for (; i >= 0; i &= i + 1, --i)
			res = max(res, F[i]);
 
		return res;
	}
};
 
int n;
pt s, f;
pt p[maxn];
 
bool read()
{
	if (scanf("%d %d %d %d %d", &s.x, &s.y, &f.x, &f.y, &n) != 5)
		return false;
 
	forn (i, n)
		scanf("%d %d", &p[i].x, &p[i].y);
 
	return true;
}
 
bool cmp(pt a, pt b) { return make_pair(a.x, a.y) < make_pair(b.x, b.y); }
 
void solve()
{
	if (s.x > f.x)
		swap(s, f);
 
	if (s.y > f.y)
	{
		f.y = 2 * s.y - f.y;
 
		forn (i, n)
			p[i].y = 2 * s.y - p[i].y;
	}
 
	vector<pt> pp;
 
	forn (i, n)
		if (s.x <= p[i].x && p[i].x <= f.x && s.y <= p[i].y && p[i].y <= f.y)
			pp.push_back(p[i]);
 
	sort(pp.begin(), pp.end(), cmp);
	fenwick dp;
 
	forn (i, size(pp))
		dp.upd(pp[i].y, dp.get(pp[i].y) + 1);
 
	int lis = dp.get(inf);
	long double ans = 0;
 
	if (lis != min(f.y - s.y, f.x - s.x) + 1)
		ans = 100.0 * (f.y - s.y + f.x - s.x) - 20.0 * lis + 5 * M_PI * lis;
	else
		ans = 100.0 * (f.y - s.y + f.x - s.x) - 20.0 * lis + 5 * M_PI * (lis - 1) + 10 * M_PI;
 
	printf("%.15f\n", (double) ans);
}
 
int main()
{
#if SEREZHKA
	freopen("file.in", "r", stdin);
#endif
 
	while (read())
		solve();
 
	return 0;
}