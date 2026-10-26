#include <bits/stdc++.h>
using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    int n, k;
    cin >> n >> k;
    vector<long long> dual, onlyA, onlyB;
    for (int i = 0; i < n; i++) {
        long long t, a, b;
        cin >> t >> a >> b;
        if (a == 1 && b == 1) dual.push_back(t);
        else if (a == 1) onlyA.push_back(t);
        else if (b == 1) onlyB.push_back(t);
    }
    sort(dual.begin(), dual.end());
    sort(onlyA.begin(), onlyA.end());
    sort(onlyB.begin(), onlyB.end());
    vector<long long> preDual(dual.size() + 1, 0), preA(onlyA.size() + 1, 0), preB(onlyB.size() + 1, 0);
    for (size_t i = 0; i < dual.size(); i++) preDual[i + 1] = preDual[i] + dual[i];
    for (size_t i = 0; i < onlyA.size(); i++) preA[i + 1] = preA[i] + onlyA[i];
    for (size_t i = 0; i < onlyB.size(); i++) preB[i + 1] = preB[i] + onlyB[i];

    long long best = -1;
    for (size_t j = 0; j <= dual.size(); j++) {
        long long need = max((long long)k - (long long)j, 0LL);
        if (need > (long long)onlyA.size() || need > (long long)onlyB.size()) continue;
        long long cost = preDual[j] + preA[need] + preB[need];
        if (best == -1 || cost < best) best = cost;
    }
    cout << best << endl;
    return 0;
}