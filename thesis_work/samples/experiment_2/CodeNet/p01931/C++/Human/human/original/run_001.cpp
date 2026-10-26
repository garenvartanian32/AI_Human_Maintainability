#include <bits/stdc++.h>

using namespace std;
typedef long long ll;

int main() {
  int n;
  string s;
  cin >> n >> s;

  int ans = 0;
  for (int i = 0; i + 1 < n; i++) {
    ans++;
    if (s[i] == 'x' && s[i + 1] == 'x') break;
    if (i + 1 == n - 1) ans++;
  }
  cout << (n == 1 ? 1 : ans) << endl;

  return 0;
}
