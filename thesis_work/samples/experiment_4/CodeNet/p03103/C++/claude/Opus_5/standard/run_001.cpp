#include <bits/stdc++.h>
using namespace std;

int main() {
    long long n, m;
    cin >> n >> m;
    vector<pair<long long,long long>> stores(n);
    for (auto &s : stores) cin >> s.first >> s.second;
    sort(stores.begin(), stores.end());
    long long totalCost = 0, remaining = m;
    for (auto &s : stores) {
        long long take = min(s.second, remaining);
        totalCost += take * s.first;
        remaining -= take;
        if (remaining == 0) break;
    }
    cout << totalCost << endl;
    return 0;
}