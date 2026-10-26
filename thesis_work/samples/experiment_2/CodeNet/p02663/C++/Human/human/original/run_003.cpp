#include <bits/stdc++.h>
using namespace std;

#define mod 1'000'000'007
typedef long long ll;

template<typename T>
void deb(initializer_list<T> l)
{
	for (auto &e : l)
		cout << e << ' ';
	cout << endl;
}

void solve()
{
	int h1, m1, h2, m2, k;
	cin >> h1 >> m1 >> h2 >> m2 >> k;

	int min1 = h1 * 60 + m1;
	int min2 = h2 * 60 + m2;
	min2 -= k;
	cout << min2 - min1 << '\n';
}

int main()
{
	ios_base::sync_with_stdio(false);
	cin.tie(nullptr);

	int t = 1;
	// cin >> t;
	while (t--)
		solve();

	return 0;
}
