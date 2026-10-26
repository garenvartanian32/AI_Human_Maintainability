#include <bits/stdc++.h>
using namespace std;
using ll = long long;

int main() {
  int n,q;
  cin >> n >> q;

  vector<ll> a(n);
  for (int i = 0; i < n; i++) {
    cin >> a[i];
  }

  while(q--) {
    ll x;
    cin >> x;

    int right = 0;
    ll sum = 0;
    ll res = 0;
    for (int left = 0; left < n; ++left) {
      while(right < n && (sum + a[right] <= x)) {
        sum += a[right];
        ++right;
      }
      res += (right - left);
      if(left == right) right++;
      else sum -= a[left];
    }
    cout << res << '\n';
  }
  return 0;
}
