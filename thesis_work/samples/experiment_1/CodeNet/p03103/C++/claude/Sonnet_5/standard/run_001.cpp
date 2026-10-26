#include <bits/stdc++.h>
using namespace std;
int main(){
    long long n,m;
    cin >> n >> m;
    vector<pair<long long,long long>> stores(n);
    for (auto &s: stores) cin >> s.first >> s.second;
    sort(stores.begin(), stores.end());
    long long total=0, remaining=m;
    for (auto &s: stores) {
        long long take = min(s.second, remaining);
        total += take*s.first;
        remaining -= take;
        if (remaining==0) break;
    }
    cout << total << endl;
    return 0;
}