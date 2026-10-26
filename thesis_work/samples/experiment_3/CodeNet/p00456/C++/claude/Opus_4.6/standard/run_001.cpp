#include <bits/stdc++.h>
using namespace std;

int main() {
    vector<int> w(10), k(10);
    for (auto &x : w) cin >> x;
    for (auto &x : k) cin >> x;
    sort(w.rbegin(), w.rend());
    sort(k.rbegin(), k.rend());
    int wScore = w[0]+w[1]+w[2];
    int kScore = k[0]+k[1]+k[2];
    cout << wScore << " " << kScore << endl;
    return 0;
}