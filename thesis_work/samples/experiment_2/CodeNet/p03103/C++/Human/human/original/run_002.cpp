#include <iostream>
#include <string>
#include <vector>
#include <cmath>
#include <algorithm>
#include <sstream>
#include <iomanip>
#include <queue>
#include <utility>

#define rep(i,n) for(int i = 0; i < n; i++)
#define rep1(i,n) for(int i = 1; i <= n; i++)
#define co(x) cout << x <<endl
#define cs(x) cout << x <<" "
typedef long long ll;

using namespace std;

int main()
{
	int N, M;
	cin >> N >> M;
	vector<pair<ll,ll>> AB(N);
	rep(i, N)
	{
		ll A, B;
		cin >> A >> B;
		AB[i].first = A;
		AB[i].second = B;
	}
	sort(AB.begin(), AB.end());

	int m = 0;
	ll ans = 0;
	rep(i, N)
	{
		if (m+AB[i].second < M)
		{
			m += AB[i].second;
			ans += AB[i].first * AB[i].second;
		}
		else
		{
			ans += AB[i].first *(M - m);
			break;
		}
	}
	co(ans);
	return 0;
}
