#include <bits/stdc++.h>
using namespace std;
int main(){
    int n;
    cin >> n;
    vector<int> a(n);
    int maxa = 0;
    for (auto &v: a) { cin >> v; maxa = max(maxa, v); }
    for (int k = maxa; k <= 300; k++) {
        long long elo = 0;
        for (auto x: a) elo += min(x, k);
        long long awruk = (long long)n*k - elo;
        if (awruk > elo) { cout << k << endl; return 0; }
    }
    cout << maxa+1 << endl;
    return 0;
}