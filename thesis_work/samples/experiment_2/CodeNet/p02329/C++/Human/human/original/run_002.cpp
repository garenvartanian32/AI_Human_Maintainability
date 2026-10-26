#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int main() {
    int n;
    long long v;
    cin >> n >> v;
    vector<long long> a(n), b(n), c(n), d(n);
    for (auto &e : a) cin >> e;
    for (auto &e : b) cin >> e;
    for (auto &e : c) cin >> e;
    for (auto &e : d) cin >> e;
    vector<long long> s, t;
    for (auto e : a) for (auto f : b) s.push_back(e + f);
    for (auto e : c) for (auto f : d) t.push_back(e + f);
    sort(s.begin(), s.end());
    sort(t.begin(), t.end());
    
    long long ans = 0;
    for (auto e : s) {
        long long val = v - e;
        ans += upper_bound(t.begin(), t.end(), val) - lower_bound(t.begin(), t.end(), val);
    }
    
    cout << ans << '\n';
    
    return 0;
}
