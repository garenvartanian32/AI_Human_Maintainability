#include <bits/stdc++.h>
using namespace std;

int main() {
    int n;
    cin >> n;
    vector<long long> a(n);
    long long total = 0, mx = 0;
    for (auto &x : a) { cin >> x; total += x; mx = max(mx, x); }
    long long k = mx;
    while (n * k <= 2 * total) k++;
    cout << k << endl;
    return 0;
}