#include <bits/stdc++.h>
using namespace std;
int main(){
    int n, m;
    cin >> n >> m;
    vector<long long> a(n), b(n);
    for (int i=0;i<n;i++) cin >> a[i] >> b[i];
    vector<long long> c(m), d(m);
    for (int i=0;i<m;i++) cin >> c[i] >> d[i];
    string out;
    for (int i=0;i<n;i++){
        int bestJ = 0;
        long long bestD = LLONG_MAX;
        for (int j=0;j<m;j++){
            long long dist = llabs(a[i]-c[j]) + llabs(b[i]-d[j]);
            if (dist < bestD) { bestD = dist; bestJ = j; }
        }
        out += to_string(bestJ+1) + "\n";
    }
    cout << out;
    return 0;
}