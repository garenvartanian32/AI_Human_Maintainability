#include <bits/stdc++.h>
using namespace std;

#define ll long long
#define mp make_pair
#define pb push_back

#define eprintf(...) fprintf(stderr, __VA_ARGS__)
#define rep(i, n) for (int i = 0; i < (int)(n); ++ i)

int main() {
	int n;
	scanf("%d", &n);
	vector <int> a(n), b(n);
	rep(i, n) scanf("%d", &a[i]);
	rep(i, n) scanf("%d", &b[i]);
	ll tot = 0;
	rep(i, n) tot += b[i] - a[i];
	ll req = 0;
	rep(i, n) if (a[i] < b[i])
		req += (b[i] - a[i] + 1) >> 1;
	puts(tot >= req ? "Yes" : "No");
	return 0;
}
