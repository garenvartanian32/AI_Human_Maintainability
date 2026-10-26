#include <bits/stdc++.h>
using namespace std;
int main(){
    long long q;
    cin >> q;
    long long n = q;
    vector<long long> factors;
    for (long long d = 2; d*d <= n; d++) {
        while (n % d == 0) { factors.push_back(d); n /= d; }
    }
    if (n > 1) factors.push_back(n);
    int K = factors.size();
    if (K <= 1) cout << 1 << endl;
    else if (K == 2) cout << 2 << endl;
    else {
        cout << 1 << endl;
        cout << factors[0]*factors[1] << endl;
    }
    return 0;
}