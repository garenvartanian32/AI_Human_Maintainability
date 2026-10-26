#include <bits/stdc++.h>
using namespace std;
int main(){
    long long n,d,a;
    cin >> n >> d >> a;
    vector<pair<long long,long long>> monsters(n);
    for (auto &m: monsters) cin >> m.first >> m.second;
    sort(monsters.begin(), monsters.end());
    priority_queue<pair<long long,long long>, vector<pair<long long,long long>>, greater<>> heap;
    long long cumulative=0, bombs=0;
    for (auto &m: monsters) {
        long long x=m.first, h=m.second;
        while (!heap.empty() && heap.top().first < x) {
            cumulative -= heap.top().second;
            heap.pop();
        }
        long long eff = h - cumulative;
        if (eff > 0) {
            long long need = (eff + a - 1)/a;
            bombs += need;
            cumulative += need*a;
            long long end = x + 2*d;
            heap.push({end, need*a});
        }
    }
    cout << bombs << endl;
    return 0;
}