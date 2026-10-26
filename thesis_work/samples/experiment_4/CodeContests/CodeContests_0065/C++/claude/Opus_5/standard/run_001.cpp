#include <bits/stdc++.h>
using namespace std;

int main() {
    long long q;
    cin >> q;
    if (q == 1) {
        cout << 1 << "\n" << 0 << "\n";
        return 0;
    }
    vector<long long> factors;
    long long n = q;
    for (long long d = 2; d * d <= n; d++) {
        while (n % d == 0) {
            factors.push_back(d);
            n /= d;
        }
    }
    if (n > 1) factors.push_back(n);
    int k = factors.size();
    if (k == 1) {
        cout << 1 << "\n" << 0 << "\n";
    } else if (k == 2) {
        cout << 2 << "\n";
    } else {
        sort(factors.begin(), factors.end());
        cout << 1 << "\n" << (factors[0] * factors[1]) << "\n";
    }
    return 0;
}