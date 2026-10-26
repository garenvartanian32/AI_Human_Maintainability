#include <bits/stdc++.h>

typedef long long ll;
using namespace std;

int main()
{
  ll N, M; cin >> N >> M;
  vector<pair<ll, ll>> a(N);
  for (int i = 0; i < N; ++i) cin >> a[i].first >> a[i].second;

  sort(a.begin(), a.end());

  ll ans = 0;
  for (auto v : a) {
    int buy_num = min(M, v.second);
    ans += buy_num * v.first;
    M -= buy_num;
  }

  cout << ans << endl;
}