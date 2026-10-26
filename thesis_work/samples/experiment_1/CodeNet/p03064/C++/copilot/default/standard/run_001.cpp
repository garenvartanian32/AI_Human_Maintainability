#include <bits/stdc++.h>
using namespace std;
using ll = long long;
constexpr int INF = 1e9;

void solve() {
    // Read input and implement solution here.
    // Example placeholder: read n and n integers, print their sum.
    int n;
    if (!(cin >> n)) return;
    vector<ll> a(n);
    for (int i = 0; i < n; ++i) cin >> a[i];
    ll sum = 0;
    for (ll x : a) sum += x;
    cout << sum << '\n';
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    solve();
    return 0;
}