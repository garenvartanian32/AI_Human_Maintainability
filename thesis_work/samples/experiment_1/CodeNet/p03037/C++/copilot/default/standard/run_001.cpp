#include <bits/stdc++.h>
using namespace std;
using ll = long long;

struct FastIO {
    FastIO() { ios::sync_with_stdio(false); cin.tie(nullptr); }
} fastio;

void solve() {
    // Read input, implement algorithm, print output.
    // Example scaffold: read n and array, print sum.
    long long n;
    if(!(cin >> n)) return; // no input
    vector<ll> a(n);
    ll sum = 0;
    for (ll &x : a) { cin >> x; sum += x; }
    cout << sum << '\n';
}

int main() {
    solve();
    return 0;
}