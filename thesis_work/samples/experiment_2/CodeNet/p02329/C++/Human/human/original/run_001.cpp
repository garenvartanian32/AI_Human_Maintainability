#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

typedef long long ll;
int main() {
    int n;
    ll v;
    cin >> n >> v;
    vector<ll> a(n), b(n), c(n), d(n);
    
    for (int i = 0; i < n; i ++) {
        cin >> a[i];
    }
    for (int i = 0; i < n; i ++) {
        cin >> b[i];
    }
    for (int i = 0; i < n; i ++) {
        cin >> c[i];
    }
    for (int i = 0; i < n; i ++) {
        cin >> d[i];
    }
    
    vector<ll> ab(n * n);
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            ab[i * n + j] = a[i] + b[j];
        }
    }
    
    sort(ab.begin(), ab.end());
    
    ll count = 0;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            ll sum = c[i] + d[j];
            count += upper_bound(ab.begin(), ab.end(), v - sum) - 
                    lower_bound(ab.begin(), ab.end(), v - sum);
        }
    }
    
    cout << count << endl;
    
    return 0;
}